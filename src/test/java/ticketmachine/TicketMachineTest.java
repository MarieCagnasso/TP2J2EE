package ticketmachine;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@Before
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation
	public void priceIsCorrectlyInitialized() {
		// Paramètres : message si erreur, valeur attendue, valeur réelle
		assertEquals("Initialisation incorrecte du prix", PRICE, machine.getPrice());
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	public void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
		assertEquals("La balance n'est pas correctement mise à jour", 10 + 20, machine.getBalance()); // Les montants ont été correctement additionnés               
	}
        
        @Test
        //S3 : on n’imprime pas le ticket si le montant inséré est insuffisant
        public void noPrintBadPrice (){
            machine.insertMoney(PRICE-10);
            assertFalse("Le ticket est imprimé alors que le montant est incorrecte",machine.printTicket());
        } 
        
        @Test
        //S4 : on n’imprime pas le ticket si le montant inséré est insuffisant
        public void printTicketPriceOk (){
            machine.insertMoney(PRICE);
            assertTrue("Le ticket n'est pas imprimé alors que le montant est correcte",machine.printTicket());
        } 
        
        @Test
        //S5 : Quand on imprime un ticket la balance est décrémentée du prix du ticket
        public void balanceDecremente (){
            machine.insertMoney(PRICE+10);
            machine.printTicket();
            assertEquals("Le ticket est imprimé la balance n'est pas décrémentée",10,machine.getBalance());
        } 
        @Test
        //S6 : Le montant collecté est mis à jour quand on imprime un ticket (pas avant)
        public void totalUpDate (){
            machine.insertMoney(PRICE);
            assertEquals("Le montant total est mis à jour avant l'impression du ticket",0,machine.getTotal());
            machine.printTicket();
            assertEquals("Le montant total n'est pas mis à jour après l'impression du ticket",PRICE,machine.getTotal());
        } 
        @Test
        //S7 : refund() rend correctement la monnaie
        public void monnaie (){
            machine.insertMoney(PRICE);
            assertEquals("La monnaie est incorrecte",PRICE,machine.refund());
        }
        
        @Test
        //S8 : refund() remet la balance à zéro
        public void monnaieBalance (){
            machine.insertMoney(PRICE);
            machine.refund();
            assertEquals("La balance n'est pas remit à zéro après le rendu de monnaie",0,machine.getBalance());
        }
        
        @Test (expected = IllegalArgumentException.class)
        //S9: on ne peut pas insérer un montant négatif
        public void montantNegatif (){
            machine.insertMoney(-1);
        }
        
        @Test (expected = IllegalArgumentException.class)
        //S9:  on ne peut pas créer de machine qui délivre des tickets dont le prix est négatif
        public void ticketNegatif (){
            TicketMachine machineTest = new TicketMachine(-1);
        }
        
}
