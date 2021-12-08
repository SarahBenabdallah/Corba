package calcul;
import org.omg.CosNaming.*; // inclure le service de nommage
import org.omg.CORBA.*; // manipuler des objets CORBA
import org.omg.CosNaming.NamingContextPackage.*;
public class Client {
public Client() {
}
public static void main (String args[]) {
try {
double mt_ht;
double taux;
double mt_ttc;
mt_ht = Double.valueOf(args[0]);
taux = Double.valueOf(args[1]);
// creer et initialiser l'ORB
ORB orb = ORB.init(args, null);
// obtenir une reference au service de nommage
org.omg.CORBA.Object objRef =
orb.resolve_initial_references("NameService");
// Utiliser NamingContextExt au lieu de NamingContext.
//car interoperable
NamingContextExt ncRef =
NamingContextExtHelper.narrow(objRef);
// demander la reference de l'objet au service de noms
String nom = "calcul_ttc";
calcul_montants calcul_ttc = calcul_montantsHelper.narrow
(ncRef.resolve_str(nom));
// faire appel a l'objet serveur et imprimer les resultats
mt_ttc = calcul_ttc.calcul_ttc(mt_ht,taux);
System.out.println("le montant ttc "+ mt_ttc);
}
catch(Exception e) {
System.out.println("Erreur : "+e);
e.printStackTrace(System.out);
}
} // fin du main
}