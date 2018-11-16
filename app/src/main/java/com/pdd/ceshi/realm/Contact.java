package com.pdd.ceshi.realm;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Contact extends RealmObject {

    public String name;
    public RealmList<Email> emails;
}
