package exo1;

/**
* exo1/calculHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from server.idl
* Tuesday, 7 December 2021 18:40:24 o'clock WAT
*/

public final class calculHolder implements org.omg.CORBA.portable.Streamable
{
  public exo1.calcul value = null;

  public calculHolder ()
  {
  }

  public calculHolder (exo1.calcul initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = exo1.calculHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    exo1.calculHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return exo1.calculHelper.type ();
  }

}
