package calcul;
import org.omg.CosNaming.*;
//inclure le package des exceptions pouvant etre generees
// par le service de nommage
import org.omg.CosNaming.NamingContextPackage.*;
// sert a manipuler les objets CORBA
import org.omg.CORBA.*;
// Classes necessaires pour referencer le POA
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
// Proprietes pour initialiser l'ORB
import java.util.Properties;
public class Calcul_Servant extends calcul_montantsPOA {
public Calcul_Servant() {
}
public double calcul_ttc(double mt_ht, double taux) {
return mt_ht*(1+taux/100);
}
}