package com.example.sidemenu;

import java.util.ArrayList;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class databasehelper extends SQLiteOpenHelper {
	public static String DATABASENAME="Check";
	public static String PRODUCTTABLE="MyItems11";
	public ArrayList cartlist=new ArrayList();
	Context c;
	public databasehelper(Context context) {
		super(context,DATABASENAME,null,33);
		c=context;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		String vquery="CREATE TABLE MyItems11(idno number)";
		db.execSQL(vquery);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	public void addProduct(ProductTable productitem)
	{
		//String vquery="CREATE TABLE MyItems11(idno number)";
		//db.execSQL(vquery);
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put("idno", productitem.idno);
		
		db.insert(PRODUCTTABLE, null, values);
		db.close();
		}
	public ArrayList getproducts()
	{
		
	SQLiteDatabase db=this.getWritableDatabase();
	Cursor cursor=db.rawQuery("SELECT * FROM " +PRODUCTTABLE,null);
	if(cursor.getCount()!=0)
	{
		if(cursor.moveToFirst())
		{
			do{
				ProductTable item=new ProductTable();
				item.idno=cursor.getInt(cursor.getColumnIndex("idno"));
			 
				cartlist.add(item.idno);
			}while(cursor.moveToNext());
			}
		}
	cursor.close();
	db.close();
	return cartlist;
	}
	public String getproductsurl(String data)
	{
		String url = null;
		SQLiteDatabase db=this.getWritableDatabase();
		Cursor cursor=db.rawQuery("SELECT  * FROM " +PRODUCTTABLE + " WHERE title='" + data + "'", null);
		if(cursor.getCount()!=0)
		{
			if(cursor.moveToFirst())
			{
				do{
			
				url=cursor.getString(cursor.getColumnIndex("rssurl"));
			Log.d("aaa",url);	
				}while(cursor.moveToNext());
			}
		}
		cursor.close();
		db.close();
		return url;
	}
	public void delete() {
		
		SQLiteDatabase db=this.getWritableDatabase();
		String vquery="DELETE FROM " +PRODUCTTABLE;
		db.execSQL(vquery);
	}
}
