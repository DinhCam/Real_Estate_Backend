import requests
import itertools
import math
from bs4 import BeautifulSoup
from selenium import webdriver
from datetime import datetime, timedelta
from dateutil.relativedelta import relativedelta
from db_util import get_re_type_id, get_address, insert_sample, insert_avg_street, insert_avg_ward, insert_avg_district


list_samples = []
current_month = datetime.now().month
current_year = datetime.now().year
# edit month_number to get data from multiple month ago
# default is 1
month_number = 1
current_date = datetime(year=int(current_year), month=int(current_month), day=1)
last_date = current_date - relativedelta(months=int(month_number))
apartment_id = get_re_type_id(['Chung cư'])
house_id = get_re_type_id(['Nhà'])
unexpected_prices = ['Giá thỏa thuận']
expected_prices = ['triệu/m²', 'triệu', 'tỷ/m²', 'tỷ']
expected_dates = ['giây trước', 'phút trước', 'giờ trước', 'hôm qua', 'ngày trước', 'tuần trước', 'tháng trước']


def convert_date(up_date):
    current_day = datetime.now().day
    new_date = datetime(year=int(current_year), month=int(current_month), day=int(current_day))
    date_plit = up_date.split(' ')
    date_size = len(date_plit)
    number = date_plit[date_size - 3]
    if expected_dates[0] in up_date or expected_dates[1] in up_date or expected_dates[2] in up_date:
        return new_date
    if expected_dates[3] in up_date:
        new_date = new_date - timedelta(days=1)
    elif expected_dates[4] in up_date:
        new_date = new_date - timedelta(days=int(number))
    elif expected_dates[5] in up_date:
        days = int(number) * 7
        new_date = new_date - timedelta(days=days)
    elif expected_dates[6] in up_date:
        new_date = new_date - relativedelta(months=int(number))
    return new_date


def download_source(url):
    html = ''
    try:
        driver = webdriver.Chrome('chromedriver.exe')
        driver.get(url)
        html = driver.page_source
    except Exception as e:
        print(e)
        return html
    return html


def convert_price(price, type, area):
    new_price = 0
    if type == -1:
        return new_price
    price = float(price.split(' ')[0])
    # 0 is triệu/m2
    if type == 0:
        new_price = price * area * 1000000
    # 1 is triệu
    elif type == 1:
        new_price = price * 1000000
    # 2 is tỷ
    elif type == 2:
        new_price = price * 1000000000
    # 3 is tỷ/m²
    else:
        new_price = price * area * 1000000000
    return math.floor(new_price)


def check_price_type(price):
    type = -1
    if unexpected_prices[0] in price:
        return type
    elif expected_prices[0] in price:
        type = 0
    elif expected_prices[1] in price:
        type = 1
    elif expected_prices[2] in price:
        type = 3
    elif expected_prices[3] in price:
        type = 2
    return type


def filter_data_for_bds(type_id, soup, html, tag, attrs):
    for a_control in soup.find_all(tag, attrs=attrs):
        try:
            product = a_control.find('div', 're__card-info')
            up_time = product.find('span', attrs='re__card-published-info-published-at').text
            up_date = convert_date(up_time.strip())
            # time_split = up_time.split("/")
            # up_date = datetime(year=int(time_split[2]), month=int(
            #     time_split[1]), day=int(time_split[0]))
            print(up_date)
            if current_date > up_date and last_date <= up_date:
                price = product.find('span', attrs='re__card-config-price').text
                type = check_price_type(price)
                area = product.find('span', attrs='re__card-config-area').text
                area = float(area.split(' ')[0])
                new_price = convert_price(price, type, area)
                if new_price > 0:
                    sub_link = 'https://batdongsan.com.vn' + a_control.get('href')
                    sub_html = download_source(sub_link)
                    sub_soup = BeautifulSoup(sub_html, 'html.parser')
                    address = ''
                    for title in sub_soup.find_all('div', attrs='detail-2 pad-16'):
                        for r in title.find_all('div', attrs='row-1'):
                            if r.find('span', attrs='r1').text == 'Địa chỉ:':
                                address = r.find('span', attrs='r2').text
                                break
                        if address != '':
                            break
                    address_plit = address.split(', ')
                    address_size = len(address_plit)
                    if address_size > 3:
                        address = get_address([address_plit[address_size-4], address_plit[address_size-3], address_plit[address_size-2]])
                        if address != None:
                            # ward_id, district_id, price, area, post_time, type
                            list_samples.append([address[1], address[3], new_price, area, up_date, type_id])
        except Exception as e:
            print(e)
            continue


def filter_data_for_ct(type_id, soup, html, tag, attrs):    
    for li_control in soup.find_all(tag, attrs=attrs):
        try:
            price = li_control.find('span', attrs='AdBody_adItemPrice__3VkVM').text
            sub_link = 'https://nha.chotot.com/' + li_control.find('a', attrs='AdItem_adItem__2O28x').get('href')
            sub_html = download_source(sub_link)
            sub_soup = BeautifulSoup(sub_html, 'html.parser')
            up_date = sub_soup.find('span', attrs='AdImage_imageCaptionText__39oDK').text
            new_date = convert_date(up_date)
            print(up_date)
            if current_date > new_date and last_date <= new_date:
                address = sub_soup.find('span', attrs='fz13').text
                type = check_price_type(price)
                area = sub_soup.find('span', attrs='AdDecription_squareMetre__2KYh8').text
                area = float(area.split(' ')[1])
                price = price.replace(',', '.').strip()
                new_price = convert_price(price, type, area)
                address_plit = address.split(', ')
                address_size = len(address_plit)
                print(address_plit[address_size-4], address_plit[address_size-3], address_plit[address_size-2])
                if address_size > 3:
                    address = get_address([address_plit[address_size-3], address_plit[address_size-2]])
                    print(address)
                    if address != None:
                        #  ward_id, district_id, price, area, post_time, type
                        list_samples.append([ address[0], address[1], new_price, area, new_date, type_id])
        except:
            continue


def is_end_of_page(soup, tag, attrs):
    div_control = soup.find(tag, attrs=attrs)
    if div_control == None:
        return False
    return True


def bds_handler(uri, type_id):
    for i in itertools.count(start=200):
        try:
            url = uri + str(i)
            html = download_source(url)
            soup = BeautifulSoup(html, 'html.parser')
            is_ended = is_end_of_page(
                soup, tag='div', attrs={'listing-empty'})
            # if i == 703:
            #     is_ended = True
            if is_ended:
                break
            filter_data_for_bds(type_id, soup, html, tag='a', attrs={'class': 'js__product-link-for-product-id'})
        except:
            continue


def ct_handler(uri, type_id):
    for i in itertools.count(start=255):
        try:
            url = uri + str(i)
            html = download_source(url)
            soup = BeautifulSoup(html, 'html.parser')
            is_ended = is_end_of_page(
                soup, tag='img', attrs={'alt':'PageNotFound'})
            print(is_ended)
            if i == 315:
                is_ended = True
            if is_ended:
                break
            filter_data_for_ct(type_id, soup, html, tag='li', attrs={'class': 'AdItem_wrapperAdItem__1hEwM'})
        except:
            continue


def filter_sample(position):
    samples = list_samples.copy()
    result = []
    while len(samples) > 0:
        first = samples[0]
        id = []
        for sample in list_samples:
            if first[position] == sample[position] and first[5] == sample[5]:
                id.append(sample)
                samples.remove(sample)
        if len(id) > 0:
            result.append(id)
    return result


def calculator():
    # streets = filter_sample(0)
    # print(streets)
    wards = filter_sample(0)
    # print(wards)
    districts = filter_sample(1)
    # print(districts)
    month = current_month - 1
    year = current_year
    if month == 0:
        month = 12
        year = int(current_year) - 1
    
    # insert for street
    # for street in streets:
    #     try:
    #         total_price = 0
    #         street_id = street[0][0]
    #         type_id = street[0][6]
    #         for item in street:
    #             price = item[3]/item[4]
    #             total_price += price
    #         avg_price = math.floor(total_price/len(street))
    #         insert_avg_street([street_id, avg_price, month, year, type_id])
    #         # print('street: ', street)
    #     except:
    #         continue
    # insert for ward
    for ward in wards:
        try:
            total_price = 0
            ward_id = ward[0][0]
            type_id = ward[0][5]
            for item in ward:
                price = item[2]/item[3]
                total_price += price
            avg_price = math.floor(total_price/len(ward))
            insert_avg_ward([ward_id, avg_price, month, year, type_id])
            # print('ward: ', ward)
        except:
            continue
    # insert for district
    for district in districts:
        try:
            total_price = 0
            district_id = district[0][1]
            type_id = district[0][5]
            for item in district:
                price = item[2]/item[3]
                total_price += price
            avg_price = math.floor(total_price/len(district))
            insert_avg_district([district_id, avg_price, month, year, type_id])
            # print('district: ', district)
        except:
            continue


if __name__ == '__main__':
    # bds_urls = ['https://batdongsan.com.vn/ban-nha-dat-tp-hcm/p',
    #             'https://batdongsan.com.vn/ban-can-ho-chung-cu-tp-hcm/p']
    ct_urls = ['https://nha.chotot.com/tp-ho-chi-minh/mua-ban-nha-dat?page=']
                # 'https://nha.chotot.com/tp-ho-chi-minh/mua-ban-can-ho-chung-cu?page=']
    # bds_handler(bds_urls[0], house_id[0])
    # bds_handler(bds_urls[1], apartment_id[0])
    ct_handler(ct_urls[0], house_id[0])
    # ct_handler(ct_urls[1], apartment_id[0])
    # list_samples = [[308, 24, 12500000000.0, 200.0, datetime(2021, 6, 29, 0, 0), 2], 
    # [317, 24, 5490000000.0, 32.0, datetime(2021, 6, 29, 0, 0), 2], 
    # [317, 24, 7490000000.0, 142.0, datetime(2021, 6, 29, 0, 0), 1], 
    # [317, 24, 3300000000.0, 242.0, datetime(2021, 6, 29, 0, 0), 1], 
    # [317, 24, 5490000000.0, 142.0, datetime(2021, 6, 29, 0, 0), 1], 
    # [317, 24, 8300000000.0, 42.0, datetime(2021, 6, 29, 0, 0), 2], 
    # [317, 12, 6490000000.0, 142.0, datetime(2021, 6, 29, 0, 0), 2], 
    # [163, 12, 8300000000.0, 74.0, datetime(2021, 6, 29, 0, 0), 1]]
    print(len(list_samples))
    if len(list_samples) > 0:
        for sample in list_samples:
            try:
                insert_sample(sample)
            except:
                continue
        calculator()
