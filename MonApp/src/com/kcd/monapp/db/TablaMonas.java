package com.kcd.monapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Clase que relaciona una tabla de BD con una lamina del album
 * @author David
 *
 */
public class TablaMonas {

	

public static final int laminaEspecial1=-1;
public static final int laminaEspecial2=-2;
public static final int laminaEspecial3=-3;

public static final String textoLamEsp1="0";
public static final String textoLamEsp2="00";
public static final String textoLamEsp3="000";

public static final int ESPECIAL=1;
public static final int ESTADIO=2;
public static final int ESCUDO=3;
public static final int EQUIPO=4;
public static final int JUGADOR=5;


private TablaDataSource dataSource;

public TablaMonas(Context context)
{
	dataSource=new TablaDataSource(context);
	dataSource.open();
	
}

public void cerrarSource()
{
	dataSource.close();
}

public void abrirSource()
{
	dataSource.open();
}
/**
 * Retorna el contador dado el texto de la lamina
 */

public int darContador(String texto)
{
	
	if(texto.equals(TablaMonas.textoLamEsp1))
	{
		return dataSource.darContador(TablaMonas.laminaEspecial1);
	}
	else if(texto.equals(TablaMonas.textoLamEsp2))
	{
		return dataSource.darContador(TablaMonas.laminaEspecial2);
		
	}
	else if(texto.equals(TablaMonas.textoLamEsp3))
	{
		return dataSource.darContador(TablaMonas.laminaEspecial3);
		
	}
	else
	{
		return dataSource.darContador(Integer.parseInt(texto));
	}
}

/**
 * Disminuye el contador
 */

public int disminuirContador(String texto)
{
	
	if(texto.equals(TablaMonas.textoLamEsp1))
	{
		int contador=dataSource.darContador(TablaMonas.laminaEspecial1);
		contador--;
		dataSource.actualizarContador(TablaMonas.laminaEspecial1, contador);
		return contador;
	}
	else if(texto.equals(TablaMonas.textoLamEsp2))
	{
		int contador=dataSource.darContador(TablaMonas.laminaEspecial2);
		contador--;
		dataSource.actualizarContador(TablaMonas.laminaEspecial2, contador);
		return contador;
		
	}
	else if(texto.equals(TablaMonas.textoLamEsp3))
	{
		int contador=dataSource.darContador(TablaMonas.laminaEspecial3);
		contador--;
		dataSource.actualizarContador(TablaMonas.laminaEspecial3, contador);
		return contador;
		
	}
	else
	{
		int contador=dataSource.darContador(Integer.parseInt(texto));
		contador--;
		dataSource.actualizarContador(Integer.parseInt(texto), contador);
		return contador;
	}
}

/**
 * Aumenta el contador
 * @param texto
 * @return
 */
public int aumentarContador(String texto)
{
	
	if(texto.equals(TablaMonas.textoLamEsp1))
	{
		int contador=dataSource.darContador(TablaMonas.laminaEspecial1);
		contador++;
		dataSource.actualizarContador(TablaMonas.laminaEspecial1, contador);
		return contador;
	}
	else if(texto.equals(TablaMonas.textoLamEsp2))
	{
		int contador=dataSource.darContador(TablaMonas.laminaEspecial2);
		contador++;
		dataSource.actualizarContador(TablaMonas.laminaEspecial2, contador);
		return contador;
		
	}
	else if(texto.equals(TablaMonas.textoLamEsp3))
	{
		int contador=dataSource.darContador(TablaMonas.laminaEspecial3);
		contador++;
		dataSource.actualizarContador(TablaMonas.laminaEspecial3, contador);
		return contador;
		
	}
	else
	{
		int contador=dataSource.darContador(Integer.parseInt(texto));
		contador++;
		dataSource.actualizarContador(Integer.parseInt(texto), contador);
		return contador;
	}
}
/**
 * Database helper
 * @author David
 *
 */
public class DatabaseHelper extends SQLiteOpenHelper {

	public static final String TABLA = "Laminas";
	public static final String COLUMN_ID= "id";
	public static final String COLUMN_NUMBER="number";
	public static final String COLUMN_COUNTER="counter";
	public static final String COLUMN_TYPE="type";
	public static final String DATABASE_NAME="monas.db";
	 private static final int DATABASE_VERSION = 1;

	  // Database creation sql statement
	  private static final String DATABASE_CREATE = "create table "
	      + TABLA + "(" + COLUMN_ID
	      + " integer primary key autoincrement, " + COLUMN_NUMBER
	      + " integer, "+COLUMN_COUNTER+" integer, "+COLUMN_TYPE+" integer);";
	  
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DATABASE_CREATE);
		
		//Las laminas especiales que van antes de la numeracion habitual
		ContentValues values= new ContentValues();
		values.put(DatabaseHelper.COLUMN_NUMBER, TablaMonas.laminaEspecial1);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.ESPECIAL);
		db.insert(DatabaseHelper.TABLA, null, values);
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, TablaMonas.laminaEspecial2);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.ESPECIAL);
		db.insert(DatabaseHelper.TABLA, null, values);
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, TablaMonas.laminaEspecial3);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.ESPECIAL);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 1
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 1);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 2
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 2);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 3
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 3);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 4
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 4);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 5
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 5);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 6
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 6);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 7
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 7);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 8
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 8);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 9
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 9);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 10
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 10);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 11
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 11);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 12
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 12);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 13
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 13);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 14
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 14);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 15
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 15);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 16
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 16);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 17
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 17);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 18
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 18);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 19
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 19);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 20
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 20);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 21
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 21);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 22
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 22);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 23
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 23);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 24
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 24);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 25
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 25);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 26
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 26);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 27
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 27);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 28
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 28);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 29
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 29);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 30
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 30);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 31
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 31);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 32
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 32);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 33
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 33);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 34
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 34);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 35
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 35);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 36
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 36);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 37
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 37);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 38
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 38);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 39
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 39);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 40
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 40);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 41
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 41);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 42
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 42);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 43
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 43);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 44
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 44);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 45
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 45);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 46
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 46);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 47
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 47);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 48
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 48);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 49
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 49);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 50
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 50);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 51
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 51);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 52
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 52);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 53
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 53);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 54
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 54);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 55
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 55);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 56
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 56);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 57
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 57);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 58
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 58);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 59
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 59);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 60
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 60);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 61
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 61);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 62
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 62);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 63
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 63);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 64
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 64);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 65
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 65);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 66
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 66);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 67
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 67);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 68
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 68);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 69
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 69);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 70
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 70);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 71
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 71);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 72
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 72);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 73
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 73);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 74
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 74);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 75
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 75);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 76
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 76);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 77
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 77);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 78
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 78);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 79
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 79);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 80
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 80);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 81
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 81);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 82
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 82);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 83
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 83);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 84
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 84);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 85
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 85);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 86
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 86);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 87
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 87);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 88
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 88);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 89
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 89);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 90
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 90);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 91
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 91);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 92
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 92);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 93
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 93);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 94
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 94);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 95
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 95);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 96
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 96);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 97
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 97);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 98
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 98);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 99
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 99);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 100
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 100);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 101
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 101);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 102
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 102);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 103
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 103);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 104
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 104);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 105
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 105);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 106
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 106);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 107
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 107);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 108
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 108);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 109
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 109);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 110
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 110);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 111
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 111);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 112
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 112);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 113
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 113);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 114
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 114);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 115
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 115);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 116
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 116);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 117
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 117);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 118
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 118);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 119
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 119);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 120
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 120);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 121
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 121);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 122
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 122);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 123
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 123);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 124
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 124);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 125
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 125);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 126
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 126);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 127
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 127);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 128
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 128);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 129
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 129);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 130
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 130);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 131
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 131);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 132
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 132);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 133
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 133);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 134
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 134);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 135
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 135);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 136
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 136);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 137
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 137);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 138
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 138);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 139
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 139);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 140
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 140);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 141
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 141);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 142
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 142);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 143
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 143);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 144
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 144);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 145
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 145);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 146
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 146);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 147
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 147);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 148
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 148);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 149
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 149);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 150
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 150);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 151
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 151);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 152
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 152);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 153
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 153);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 154
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 154);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 155
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 155);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 156
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 156);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 157
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 157);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 158
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 158);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 159
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 159);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 160
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 160);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 161
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 161);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 162
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 162);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 163
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 163);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 164
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 164);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 165
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 165);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 166
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 166);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 167
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 167);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 168
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 168);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 169
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 169);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 170
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 170);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 171
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 171);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 172
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 172);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 173
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 173);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 174
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 174);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 175
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 175);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 176
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 176);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 177
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 177);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 178
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 178);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 179
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 179);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 180
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 180);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 181
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 181);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 182
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 182);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 183
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 183);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 184
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 184);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 185
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 185);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 186
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 186);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 187
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 187);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 188
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 188);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 189
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 189);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 190
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 190);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 191
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 191);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 192
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 192);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 193
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 193);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 194
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 194);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 195
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 195);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 196
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 196);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 197
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 197);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 198
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 198);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 199
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 199);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 200
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 200);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 201
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 201);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 202
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 202);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 203
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 203);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 204
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 204);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 205
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 205);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 206
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 206);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 207
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 207);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 208
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 208);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 209
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 209);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 210
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 210);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 211
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 211);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 212
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 212);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 213
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 213);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 214
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 214);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 215
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 215);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 216
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 216);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 217
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 217);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 218
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 218);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 219
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 219);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 220
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 220);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 221
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 221);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 222
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 222);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 223
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 223);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 224
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 224);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 225
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 225);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 226
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 226);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 227
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 227);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 228
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 228);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 229
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 229);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 230
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 230);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 231
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 231);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 232
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 232);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 233
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 233);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 234
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 234);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 235
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 235);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 236
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 236);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 237
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 237);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 238
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 238);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 239
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 239);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 240
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 240);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 241
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 241);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 242
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 242);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 243
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 243);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 244
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 244);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 245
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 245);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 246
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 246);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 247
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 247);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 248
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 248);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 249
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 249);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 250
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 250);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 251
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 251);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 252
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 252);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 253
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 253);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 254
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 254);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 255
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 255);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 256
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 256);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 257
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 257);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 258
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 258);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 259
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 259);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 260
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 260);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 261
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 261);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 262
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 262);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 263
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 263);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 264
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 264);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 265
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 265);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 266
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 266);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 267
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 267);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 268
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 268);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 269
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 269);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 270
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 270);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 271
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 271);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 272
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 272);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 273
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 273);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 274
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 274);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 275
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 275);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 276
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 276);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 277
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 277);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 278
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 278);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 279
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 279);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 280
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 280);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 281
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 281);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 282
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 282);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 283
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 283);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 284
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 284);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 285
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 285);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 286
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 286);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 287
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 287);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 288
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 288);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 289
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 289);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 290
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 290);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 291
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 291);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 292
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 292);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 293
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 293);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 294
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 294);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 295
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 295);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 296
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 296);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 297
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 297);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 298
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 298);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 299
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 299);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 300
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 300);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 301
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 301);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 302
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 302);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 303
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 303);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 304
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 304);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 305
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 305);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 306
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 306);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 307
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 307);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 308
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 308);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 309
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 309);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 310
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 310);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 311
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 311);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 312
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 312);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 313
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 313);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 314
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 314);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 315
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 315);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 316
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 316);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 317
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 317);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 318
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 318);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 319
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 319);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 320
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 320);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 321
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 321);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 322
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 322);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 323
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 323);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 324
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 324);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 325
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 325);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 326
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 326);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 327
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 327);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 328
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 328);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 329
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 329);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 330
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 330);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 331
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 331);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 332
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 332);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 333
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 333);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 334
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 334);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 335
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 335);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 336
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 336);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 337
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 337);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 338
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 338);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 339
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 339);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 340
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 340);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 341
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 341);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 342
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 342);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 343
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 343);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 344
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 344);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 345
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 345);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 346
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 346);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 347
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 347);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 348
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 348);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 349
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 349);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 350
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 350);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 351
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 351);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 352
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 352);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 353
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 353);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 354
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 354);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 355
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 355);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 356
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 356);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 357
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 357);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 358
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 358);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 359
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 359);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 360
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 360);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 361
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 361);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 362
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 362);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 363
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 363);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 364
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 364);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 365
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 365);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 366
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 366);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 367
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 367);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 368
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 368);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 369
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 369);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 370
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 370);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 371
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 371);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 372
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 372);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 373
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 373);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 374
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 374);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 375
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 375);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 376
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 376);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 377
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 377);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 378
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 378);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 379
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 379);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 380
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 380);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 381
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 381);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 382
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 382);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 383
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 383);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 384
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 384);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 385
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 385);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 386
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 386);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 387
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 387);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 388
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 388);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 389
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 389);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 390
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 390);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 391
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 391);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 392
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 392);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 393
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 393);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 394
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 394);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 395
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 395);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 396
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 396);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 397
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 397);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 398
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 398);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 399
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 399);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 400
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 400);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 401
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 401);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 402
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 402);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 403
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 403);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 404
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 404);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 405
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 405);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 406
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 406);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 407
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 407);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 408
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 408);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 409
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 409);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 410
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 410);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 411
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 411);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 412
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 412);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 413
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 413);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 414
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 414);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 415
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 415);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 416
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 416);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 417
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 417);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 418
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 418);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 419
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 419);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 420
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 420);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 421
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 421);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 422
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 422);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 423
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 423);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 424
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 424);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 425
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 425);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 426
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 426);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 427
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 427);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 428
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 428);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 429
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 429);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 430
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 430);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 431
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 431);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 432
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 432);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 433
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 433);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 434
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 434);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 435
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 435);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 436
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 436);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 437
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 437);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 438
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 438);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 439
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 439);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 440
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 440);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 441
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 441);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 442
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 442);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 443
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 443);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 444
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 444);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 445
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 445);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 446
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 446);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 447
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 447);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 448
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 448);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 449
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 449);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 450
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 450);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 451
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 451);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 452
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 452);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 453
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 453);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 454
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 454);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 455
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 455);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 456
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 456);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 457
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 457);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 458
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 458);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 459
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 459);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 460
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 460);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 461
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 461);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 462
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 462);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 463
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 463);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 464
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 464);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 465
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 465);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 466
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 466);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 467
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 467);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 468
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 468);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 469
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 469);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 470
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 470);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 471
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 471);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 472
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 472);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 473
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 473);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 474
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 474);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 475
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 475);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 476
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 476);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 477
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 477);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 478
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 478);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 479
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 479);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 480
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 480);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 481
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 481);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 482
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 482);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 483
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 483);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 484
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 484);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 485
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 485);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 486
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 486);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 487
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 487);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 488
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 488);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 489
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 489);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 490
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 490);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 491
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 491);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 492
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 492);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 493
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 493);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 494
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 494);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 495
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 495);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 496
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 496);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 497
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 497);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 498
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 498);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 499
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 499);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 500
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 500);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 501
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 501);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 502
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 502);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 503
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 503);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 504
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 504);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 505
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 505);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 506
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 506);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 507
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 507);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 508
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 508);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 509
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 509);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 510
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 510);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 511
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 511);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 512
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 512);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 513
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 513);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 514
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 514);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 515
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 515);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 516
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 516);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 517
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 517);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 518
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 518);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 519
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 519);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 520
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 520);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 521
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 521);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 522
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 522);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 523
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 523);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 524
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 524);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 525
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 525);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 526
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 526);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 527
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 527);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 528
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 528);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 529
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 529);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 530
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 530);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 531
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 531);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 532
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 532);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 533
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 533);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 534
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 534);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 535
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 535);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 536
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 536);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 537
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 537);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 538
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 538);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 539
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 539);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 540
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 540);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 541
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 541);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 542
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 542);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 543
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 543);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 544
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 544);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 545
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 545);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 546
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 546);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 547
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 547);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 548
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 548);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 549
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 549);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 550
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 550);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 551
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 551);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 552
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 552);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 553
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 553);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 554
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 554);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 555
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 555);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 556
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 556);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 557
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 557);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 558
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 558);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 559
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 559);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 560
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 560);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 561
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 561);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 562
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 562);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 563
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 563);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 564
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 564);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 565
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 565);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 566
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 566);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 567
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 567);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 568
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 568);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 569
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 569);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 570
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 570);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 571
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 571);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 572
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 572);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 573
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 573);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 574
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 574);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 575
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 575);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 576
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 576);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 577
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 577);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 578
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 578);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 579
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 579);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 580
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 580);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 581
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 581);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 582
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 582);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 583
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 583);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 584
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 584);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 585
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 585);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 586
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 586);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 587
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 587);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 588
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 588);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 589
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 589);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 590
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 590);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 591
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 591);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 592
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 592);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 593
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 593);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 594
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 594);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 595
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 595);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 596
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 596);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 597
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 597);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 598
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 598);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 599
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 599);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 600
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 600);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 601
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 601);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 602
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 602);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 603
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 603);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 604
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 604);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 605
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 605);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 606
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 606);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 607
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 607);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 608
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 608);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 609
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 609);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 610
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 610);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 611
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 611);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 612
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 612);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 613
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 613);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 614
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 614);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 615
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 615);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 616
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 616);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 617
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 617);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 618
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 618);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 619
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 619);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 620
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 620);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 621
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 621);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 622
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 622);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 623
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 623);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 624
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 624);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 625
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 625);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 626
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 626);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 627
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 627);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 628
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 628);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 629
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 629);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 630
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 630);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 631
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 631);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 632
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 632);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 633
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 633);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 634
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 634);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 635
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 635);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 636
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 636);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 637
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 637);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 638
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 638);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 639
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 639);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 640
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 640);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 641
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 641);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 642
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 642);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 643
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 643);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 644
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 644);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 645
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 645);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 646
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 646);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 647
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 647);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 648
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 648);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 649
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 649);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 650
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 650);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 651
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 651);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 652
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 652);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 653
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 653);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 654
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 654);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 655
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 655);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 656
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 656);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 657
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 657);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 658
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 658);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 659
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 659);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 660
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 660);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 661
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 661);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 662
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 662);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 663
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 663);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 664
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 664);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 665
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 665);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 666
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 666);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 667
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 667);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 668
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 668);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 669
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 669);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 670
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 670);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 671
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 671);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 672
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 672);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 673
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 673);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 674
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 674);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 675
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 675);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 676
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 676);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 677
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 677);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 678
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 678);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 679
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 679);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 680
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 680);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 681
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 681);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 682
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 682);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 683
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 683);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 684
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 684);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 685
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 685);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 686
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 686);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 687
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 687);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 688
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 688);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 689
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 689);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 690
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 690);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 691
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 691);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 692
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 692);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 693
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 693);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 694
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 694);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 695
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 695);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 696
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 696);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 697
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 697);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 698
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 698);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 699
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 699);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);
		//Lamina 700
		values.clear();
		values.put(DatabaseHelper.COLUMN_NUMBER, 700);
		values.put(DatabaseHelper.COLUMN_COUNTER, 0);
		values.put(DatabaseHelper.COLUMN_TYPE, TablaMonas.JUGADOR);
		db.insert(DatabaseHelper.TABLA, null, values);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	
}

/**
 * DAO para ejecutar los querys a la BD
 * @author David
 *
 */
public class TablaDataSource {
	
private SQLiteDatabase database;
private DatabaseHelper dbHelper;

public TablaDataSource(Context context)
{
	dbHelper= new DatabaseHelper(context);	
}

public void open() throws SQLException {
    database = dbHelper.getWritableDatabase();
  }

  public void close() {
    dbHelper.close();
  }


  public int darContador (int numeroLamina)
  {
	  
	  String[] contador = { DatabaseHelper.COLUMN_COUNTER};
	  Cursor cursor =database.query(DatabaseHelper.TABLA, contador, DatabaseHelper.COLUMN_NUMBER+" = "+numeroLamina, null, null, null, null);
	  cursor.moveToFirst();
	  int retorno= cursor.getInt(0);
	  cursor.close();
	  return retorno;
	  
  }
  
  public void actualizarContador(int numeroLamina,int contadorNuevo)
  {
	  ContentValues values= new ContentValues();
		values.put(DatabaseHelper.COLUMN_COUNTER, contadorNuevo);
	  database.update(DatabaseHelper.TABLA, values,DatabaseHelper.COLUMN_NUMBER+" = "+numeroLamina , null);
  }
}
}
