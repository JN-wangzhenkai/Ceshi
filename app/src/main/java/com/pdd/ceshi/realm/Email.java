package com.pdd.ceshi.realm;

import io.realm.Realm;
import io.realm.RealmObject;

public class Email extends RealmObject {

    public String address;
    public boolean active;
}
