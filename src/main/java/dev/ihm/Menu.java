package dev.ihm;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.stereotype.Controller;

import dev.exception.PlatException;
import dev.ihm.options.IOptionMenu;
import dev.service.IPlatService;

@Controller
public class Menu {

    private Map<Integer, IOptionMenu> actions = new HashMap<>();

    private String menu;
    private Scanner scanner;
    
    public Menu(Scanner scanner, IPlatService service, List<IOptionMenu> options) {
    	//On utilise un array pour pouvoir en modifier la valeur dans le lambda
	    int[] i= {1};
	    options.stream()
	    //On range les options en fonction de leur poids
	    .sorted(Comparator.comparing(IOptionMenu::getPoids))
	    //La clé est choisie comme la valeur de l'index puis est incrémentée, sauf si le poids est de 99 (auquel cas la clé est de 99)
	    .forEachOrdered(o -> actions.put((o.getPoids()!=99)?i[0]++:99, o));
	    this.scanner = scanner;
  }

//    public Menu(Scanner scanner, IPlatService service) {
//        actions.put(1, new OptionListerPlats(service));
//        actions.put(2, new OptionAjouterPlat(scanner, service));
//        actions.put(99, new OptionTerminer());
//        this.scanner = scanner;
//    }

    public void afficher() {

        boolean continuer = true;

        while (continuer) {

            System.out.println(getMenuTexte());

            int choix = this.scanner.nextInt();

            try {
                this.actions.get(choix).executer();
            } catch (PlatException e) {
                continuer = false;
                System.out.println(e.getMessage());
            }
        }
    }

    private String getMenuTexte() {
        if (menu == null) {
            StringBuilder sb = new StringBuilder();
            sb.append("** Restaurant Console App **");
            sb.append("\n");
            this.actions.forEach((index, option) -> {
                sb.append(index);
                sb.append(". ");
                sb.append(option.getTitre());
                sb.append("\n");
            });
            this.menu = sb.toString();
        }
        return this.menu;
    }
}
