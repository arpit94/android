package com.androidhive.pushnotifications;

public class Contact {
long id;
String name;
String key;
public Contact(long id,String name,String key)
{
	this.id=id;
	this.name=name;
	this.key=key;
}
void set_id(long id){this.id=id;}
void set_name(String name){this.name=name;}
void set_key(String key){this.key=key;}
public long get_id(){return id;}
public String get_name(){return name;}
public String get_key(){return key;}

}
