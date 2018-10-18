package com.example.hp.studentfees;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "StudentFees";

    private static final String TABLE_Fees = "Fees";
    private static final String TABLE_User = "User";
    private static final String TABLE_FeesStatus = "FeesStatus";
    private static final String TABLE_DedLine = "DedLine";

    private static final String Col_Id = "Id";
    private static final String Col_Tution_Fees = "TutionFees";
    private static final String Col_Stp_Fees = "StpFees";
    private static final String Col_ExtraActivity_Fees = "ExtraActivityFees";
    private static final String Col_Hostel_Fees = "HostelFees";
    private static final String Col_Total_Fees = "TotalFees";

    private static final String Col_Password = "Password";

    private static final String Col_Status = "Status";

    private static final String Col_Check_Date = "CheckDate";
    private static final String Col_MobileNo = "MobileNo";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String CREATE_TABLE_USER = "CREATE TABLE " + TABLE_User + "(" + Col_Id + " TEXT PRIMARY KEY,"
            + Col_Password + " TEXT" + ")";

    private static final String CREATE_TABLE_FEES = "CREATE TABLE " + TABLE_Fees
            + "(" + Col_Id + " TEXT," + Col_Tution_Fees + " TEXT,"
            + Col_Stp_Fees + " TEXT," + Col_ExtraActivity_Fees + " TEXT," + Col_Hostel_Fees + " TEXT,"+ Col_Total_Fees + " TEXT,FOREIGN KEY ("+ Col_Id
            + ") REFERENCES " + TABLE_Fees + "("+ Col_Id +")" + ")";

    private static final String CREATE_TABLE_FEESSTATUS = "CREATE TABLE " + TABLE_FeesStatus + "(" + Col_Id + " TEXT," + Col_Status + " TEXT,FOREIGN KEY ("+ Col_Id
            + ") REFERENCES " + TABLE_Fees + "("+ Col_Id +")" + ")";

    private static final String CREATE_TABLE_DEDLINE = "CREATE TABLE " + TABLE_DedLine + "(" + Col_Id + " TEXT,"
            + Col_Check_Date + " TEXT," + Col_MobileNo +" TEXT,FOREIGN KEY ("+ Col_Id + ") REFERENCES " + TABLE_Fees + "("+ Col_Id +")" + ")";


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_FEES);
        db.execSQL(CREATE_TABLE_FEESSTATUS);
        db.execSQL(CREATE_TABLE_DEDLINE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_User);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Fees);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FeesStatus);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DedLine);
        onCreate(db);
    }

    public void insertDataIntoFees(String id,String tutionfees,String stpfees,String extraactivityfees,String hostelfees,String totalfees) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_Id,id);
        contentValues.put(Col_Tution_Fees,tutionfees);
        contentValues.put(Col_Stp_Fees,stpfees);
        contentValues.put(Col_ExtraActivity_Fees,extraactivityfees);
        contentValues.put(Col_Hostel_Fees,hostelfees);
        contentValues.put(Col_Total_Fees,totalfees);
        db.insert(TABLE_Fees,null ,contentValues);
    }

    public void insertDataIntoUser(String id,String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_Id,id);
        contentValues.put(Col_Password,password);
        db.insert(TABLE_User,null ,contentValues);
    }

    public void insertDataIntoFeesStatus(String id,String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_Id,id);
        contentValues.put(Col_Status,status);
        db.insert(TABLE_FeesStatus,null ,contentValues);
    }

    public void insertDataIntoFeeDedLine(String id,String dedline,String mobileno) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col_Id,id);
        contentValues.put(Col_Check_Date,dedline);
        contentValues.put(Col_MobileNo,mobileno);
        db.insert(TABLE_DedLine,null ,contentValues);
    }

    public boolean UpdateStatus(String key)
    {
        boolean id = false;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("UPDATE "+ TABLE_FeesStatus + " SET " + Col_Status + "='" + 1 + "' WHERE Id = '"+key+"';",null );
        if(cursor.getCount()>=0) {
            id = true;
        }
        return id;
    }



    public int checkUser(User us)
    {
        int id=-1;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT Id FROM User WHERE Id=? AND Password=?",new String[]{us.getId(),us.getPassword()});
        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            id=cursor.getInt(0);
            cursor.close();
        }
        return id;
    }

    public List<DedLine> getDedLine(String key ){

        List<DedLine> ded_line=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();


        Cursor c =db.rawQuery("SELECT * FROM DedLine where Id = '"+key+"';",null);


        if(c.moveToFirst()){
            do{
                DedLine Dedline =new DedLine(c.getString(0),c.getString(1),c.getString(2));
                ded_line.add(Dedline);
            }while(c.moveToNext());
        }

        return ded_line;
    }

    public List<Student> getFees(String key ){

        List<Student> studentFeesDetails=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();


        Cursor c =db.rawQuery("SELECT * FROM Fees where Id = '"+key+"';",null);


        if(c.moveToFirst()){
            do{
                Student student =new Student(c.getString(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5));
                studentFeesDetails.add(student);
            }while(c.moveToNext());
        }

        return studentFeesDetails;
    }

    public List<Status> getStatus(String key ){

        List<Status> FeesStatus=new ArrayList<>();
        SQLiteDatabase db=getReadableDatabase();


        Cursor c =db.rawQuery("SELECT * FROM FeesStatus where Id = '"+key+"';",null);


        if(c.moveToFirst()){
            do{
                Status status =new Status(c.getString(0),c.getString(1));
                FeesStatus.add(status);
            }while(c.moveToNext());
        }

        return FeesStatus;
    }
}

