package packageusage;

import package2.Package2Class;
import org.jsoup.nodes.Node;

import java.util.Scanner;
//import org.w3c.dom.Node;olmaz cunku complier sasırır hangi packageı kullanacagını

public class UsageOfPackage
{
    public static void main(String[] args)
    {
        //bircok Node classı var farklı packagelarda
        Node node=null;
        org.w3c.dom.Node node2=null;
        Package2Class object=null;


        Deneme d=new Deneme();//aynı packagedaki classı import etmene gerek yok
        //bir package'da aynı isimde classlar olamaz



    }
}
class Deneme
{

}