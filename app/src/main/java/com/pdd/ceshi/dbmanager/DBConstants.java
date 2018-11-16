package com.pdd.ceshi.dbmanager;

public class DBConstants {
    public static final String RECORD_DB_NAME = "record_db";

    public static final String HISTORY_TABLENAME = "history";
    public static final String CREAT_HISTORY = "create table " + HISTORY_TABLENAME
            + "(_id integer primary key autoincrement, h_name text not null)";
}
