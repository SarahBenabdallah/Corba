package exo1;

import org.omg.CORBA.*;

import java.io.*;

import java.lang.*;

import java.util.*;

import org.omg.CosNaming.*;

import org.omg.CosNaming.NamingContextPackage.*;

public class Client {
    public static void main(String[] args) throws IOException {
        ////////////////////////////////////////////////////
        // On initialise l'ORB
        ////////////////////////////////////////////////////
        ORB orb = ORB.init(args, null);

        if (args.length != 1) {
            System.err.println("utilisation : Client nombre");
            System.exit(1);
        }


        //////////////////////////////////////
        //PARTIE EXO2///////
        /////////////////////////////////////
         
        // try {
        // // obtenir une reference au service de nommage
        // org.omg.CORBA.Object objRef =
        // orb.resolve_initial_references("NameService");
        // // Utiliser NamingContextExt au lieu de NamingContext.
        // //car interoperable
        // NamingContextExt ncRef =
        // NamingContextExtHelper.narrow(objRef);
        // // demander la reference de l'objet au service de noms
        // String nom = "exo2";
        // calcul incdec = calculHelper.narrow
        // (ncRef.resolve_str(nom));
        // }catch (Exception e) {
        // 	System.out.println(e);
        // }

        ////////////////////////////////////////////////////
        // Recuperation de la reference d'objet du serveur
        // Dans cet exemple, la reference est stockee sous
        // la forme d'une chaine de caracteres (IOR) dans un
        // fichier. A ce stade, il est bien sur possible 
        // d'invoquer un service de nommage.
        ////////////////////////////////////////////////////
        String ior = null;

        try {
            String ref = "calcul.ref";
            FileInputStream file = new FileInputStream(ref);
            BufferedReader in = new BufferedReader(new InputStreamReader(file));
            ior = in.readLine();
            file.close();
        } catch (IOException ex) {
            System.err.println("Impossible de lire fichier : `" +
                ex.getMessage() + "'");
            System.exit(1);
        }

        ////////////////////////////////////////////////////
        // Construction d'une reference d'objet, non type d'abord,
        // puis "cast" en une reference sur l'interface 
        // "calcul"  avec narrow (generation d'une souche)
        ////////////////////////////////////////////////////
        org.omg.CORBA.Object obj = orb.string_to_object(ior);

        if (obj == null) {
            System.err.println("Erreur sur string_to_object() ");
            throw new RuntimeException();
        }

        calcul calc = calculHelper.narrow(obj);

        if (calc == null) {
            System.err.println("Erreur sur narrow() ");
            throw new RuntimeException();
        }

        ////////////////////////////////////////////////////
        // Invocation du serveur
        ////////////////////////////////////////////////////
        char car;

        Integer param = new Integer(args[0]);

        IntHolder resultat = new IntHolder(param.intValue());

        try {
            System.out.println(" Que faire (incrementer ou decrementer ; saisir i ou d)?  ");
            car = (char) System.in.read();

            if (car == 'i') {
                calc.incrementer(resultat);

                System.out.println(" Valeur incrementee = " + resultat.value);
            } else if (car == 'd') {
                calc.decrementer(resultat);

                System.out.println(" Valeur decrementee = " + resultat.value);
            } else {
                System.out.println("  Saisir 'i' ou 'd'");
            }
        } catch (IOException ex) {
            System.out.println("Erreur lecture commande (char)");
            System.exit(1);
        }

        System.exit(0);
    }
}