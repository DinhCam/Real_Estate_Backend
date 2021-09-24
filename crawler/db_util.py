import mysql.connector

mydb = mysql.connector.connect(
    host= "localhost",
    # host = "real-estate.ct91dcxnxdsk.ap-southeast-1.rds.amazonaws.com",
    user="root",
    password="nobita",
    database="real_estate"
)

mycursor = mydb.cursor(buffered=True)


def get_re_type_id(val):
    mycursor.execute("select id from real_estate_type where name = %s", val)
    return mycursor.fetchone()


def get_address(val):
    sql = 'select w.id, d.id as distric_id from ward w join district d on w.name = %s and d.id = w.district_id and d.name = %s'
    mycursor.execute(sql, val)
    return mycursor.fetchone()


def insert_sample(val):
    sql = 'insert into sample(ward_id, district_id, price, area, post_time, type) values(%s, %s, %s, %s, %s, %s)'
    mycursor.execute(sql, val)
    mydb.commit()


def insert_avg_street(val):
    sql = 'insert into average_price(street_id, price, month, year, type) values(%s, %s, %s, %s, %s)'
    mycursor.execute(sql, val)
    mydb.commit()


def insert_avg_ward(val):
    sql = 'insert into average_price(ward_id, price, month, year, type) values(%s, %s, %s, %s, %s)'
    mycursor.execute(sql, val)
    mydb.commit()


def insert_avg_district(val):
    sql = 'insert into average_price(district_id, price, month, year, type) values(%s, %s, %s, %s, %s)'
    mycursor.execute(sql, val)
    mydb.commit()