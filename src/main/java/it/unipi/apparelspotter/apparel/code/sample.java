package it.unipi.apparelspotter.apparel.code;

public class sample {


    //getting data from database
    /*
    *
                    case 3:
                        customerService.getAllNeo4jCustomers().forEach(System.out::println);
                        break;

                    case 4:
                        System.out.print("Enter Id: ");
                        String id = scanner.nextLine();
                        // Assuming there is a similar method for Neo4j customers
                        Optional<CustomerMongo> neoCustomerOptional = customerService.getMongoCustomerById(id);
                        neoCustomerOptional.ifPresentOrElse(
                                customer -> System.out.println("Customer Name: " + customer.getFirstName() + " " + customer.getLastName()),
                                () -> System.out.println("Customer not found.")
                        );
                        break;
                        *
                        *
                        *
                        *                     case 5:
                        customerService.getAllMongoCustomers().forEach(System.out::println);
                        break;

                    case 2:
                        System.out.print("Enter Id: ");
                        String customerId = scanner.nextLine();
                        Optional<CustomerMongo> customerOptional = customerService.getMongoCustomerById(customerId);
                        customerOptional.ifPresentOrElse(
                                customer -> System.out.println("Customer Name: " + customer.getFirstName() + " " + customer.getLastName()),
                                () -> System.out.println("Customer not found.")
                        );
                        break;
*/
}




//login
  /*retailerOptional.ifPresentOrElse(
                        retailer -> {
                            String oldPassword = retailer.getPassword();
                            if(oldPassword==password){
                                customerPage.customerpage();
                                System.out.println("newPassword: " + password);
                                System.out.println("OldPassword: " + oldPassword);
                            }
                        },
                        () -> {
                            System.out.println("Retailer not found.");
                        }
                );*/

// @Query("MATCH (retailer:Retailer) WHERE retailer._id = $retailerId RETURN retailer")
//    Optional<RetailerNeo4j> findBy_Id(String retailerId);



            /*ClothDetail clothDetails = clothService.getClothDetailsById(id);

            if (clothDetails != null) {
                System.out.print("Cloth Name: " + clothDetails.getItem_name());
                System.out.print("Cloth Category: " + clothDetails.getCategory());
                System.out.print("Cloth Type: " + clothDetails.getType());
                System.out.print("Cloth Brand: " + clothDetails.getBrand());
                System.out.print("Cloth Size: " + clothDetails.getSize());
                System.out.print("Cloth Price: " + clothDetails.getPrice());
                System.out.print("Cloth ImageURL: " + clothDetails.getImageUrl());
                System.out.print("Cloth PostedDate: " + clothDetails.getPostedDate());
            } else {
                System.out.println("Cloth details not available.");
            }*/