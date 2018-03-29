alter table kv_taxi_info add ( create_time date null  );
update kv_taxi_info k set k.create_time=to_date('2017-11-28 10:00:00','yyyy-MM-dd HH:mi:ss') where k.create_time is null;

alter table kv_taxi_driver_info add ( create_time date null  );
update kv_taxi_driver_info k set k.create_time=to_date('2017-11-28 10:00:00','yyyy-MM-dd HH:mi:ss') where k.create_time is null;
