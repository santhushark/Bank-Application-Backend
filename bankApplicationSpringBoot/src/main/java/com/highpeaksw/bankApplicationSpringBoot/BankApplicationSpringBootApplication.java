package com.highpeaksw.bankApplicationSpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class BankApplicationSpringBootApplication {

    public static void main(String[] args) {

//        new SpringApplicationBuilder(BankApplicationSpringBootApplication.class)
//                .web(WebApplicationType.NONE)
//                .run(args);

        SpringApplication.run(BankApplicationSpringBootApplication.class, args);
		/*char choice;
		Scanner sf = new Scanner(System.in);
		System.out.println("      $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("      $                                      $");
		System.out.println("      $        STATE BANK OF HIGHPEAK        $");
		System.out.println("      $                                      $");
		System.out.println("      $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        BankingOperations bankingOperations = new BankingOperations();
		do {
			System.out.println();
			System.out.println("PLEASE enter the kind of operation you want to perform, SELECT A or B");
			System.out.println();
			System.out.println("****  A. CREATE ACCOUNT" + "    " + "B. NET BANKING        *****");
			choice = Character.toUpperCase(sf.next().charAt(0));
			switch (choice) {

				case 'A': {
					BankCustomer customer = new BankCustomer();
					bankingOperations.initCreateAccount(customer);
				}
				break;

				case 'B': {
					bankingOperations.initNetBanking(null);
				}
				break;

				default: {
					System.out.println("THERE IS NO OPERATION ASSOCIATED WITH THE OPTION YOU HAVE ENTERED");
				}
				break;

			}

			System.out.println("PRESS Y/y to continue BANKING else any other character to EXIT.");
			choice = sf.next().charAt(0);

		} while (choice == 'Y' || choice == 'y');*/
    }
}

