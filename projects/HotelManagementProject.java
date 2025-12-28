import java.util.ArrayList;
import java.util.Scanner;

public class HotelManagementProject {

    // Room class
    static class Room {
        static final int SINGLE = 1;
        static final int DOUBLE = 2;

        int roomType;      // 1 for single, 2 for double
        int roomNumber;
        boolean isBooked = false;

        void roomInputs(Scanner sc) {
            while (true) {
                System.out.println("Enter room type: 1. Single Bed 2. Double Bed");
                roomType = sc.nextInt();
                if (roomType == SINGLE || roomType == DOUBLE) {
                   System.out.println("Enter room number:");
                   roomNumber = sc.nextInt();
                   break; // valid input, exit loop
                } else {
                   System.out.println("Please enter a valid room type (1 or 2). Try again.");
                }
           }
        }


        void display() {
            System.out.println("Room Details:");
            System.out.println("Room Number: " + roomNumber);
            System.out.println("Room Type: " +
                    (roomType == SINGLE ? "Single Bedded Room" : "Double Bedded Room"));
            System.out.println("Booked: " + (isBooked ? "Yes" : "No"));
        }
    }

    // Customer class
    static class Customer {
        String name;
        String contactNumber;
        Room bookedRoom;
        int food;
        int count;
        ArrayList<Food> orderedFood = new ArrayList<>();

        void customerDetailsInput(Scanner sc, ArrayList<Room> rooms) {
           
            Room selectedRoom = null;
            

            while (selectedRoom == null) {
                System.out.println("Enter the room number you want to book:");
                int roomNumber = sc.nextInt();

                boolean found = false;
                for (Room r : rooms) {
                    if (r.roomNumber == roomNumber) {
                        found = true;
                        if (r.isBooked) {
                            System.out.println("Room already booked! Choose another room.");
                        } else {
                            r.isBooked = true;
                            selectedRoom = r;
                        }
                        break;
                    }
                }

                if (!found) {
                    System.out.println("Room number not found. Please try again.");
                }
            }

            bookedRoom = selectedRoom;

            sc.nextLine(); // consume leftover newline
            System.out.print("Enter your name: ");
            name = sc.nextLine();
            do{
                System.out.print("Enter your contact number: ");
               contactNumber = sc.nextLine();
                if(contactNumber.length()==10){
                    System.out.println("valid contant number");
                }else{
                    System.out.println("enetr valid contant number of 10 digits");
                }
            }while(contactNumber.length()!=10);
            System.out.println("you need Food 1.yes 2.no");
            food=sc.nextInt();
            if(food==1){
                count++;
            }

            System.out.println("Room booked successfully!");
        }

        void customerDetails() {
            if (bookedRoom != null && bookedRoom.isBooked) {
                System.out.println("\nCustomer Details:");
                System.out.println("Name: " + name);
                System.out.println("Contact Number: " + contactNumber);
                System.out.println("Room Number: " + bookedRoom.roomNumber);
                System.out.println("Room Type: " +
                        (bookedRoom.roomType == Room.SINGLE
                                ? "Single Bedded Room"
                                : "Double Bedded Room"));
            } else {
                System.out.println("\nNo room booked.");
            }
        }
    }
    static class Food{
        String name;
        int quantity;
        double price;
        int items;
       
        void inputFoodDetails(Scanner sc) {
            System.out.println("enter food name");
            name=sc.nextLine();
            do{
            System.out.println("enter quantity available");
            quantity=sc.nextInt();
            if(quantity==0|| quantity<0){
                System.out.println("enter valid quantity");
            }
            }while(quantity<=0);
            do{
            System.out.println("enter price for 1 qunatity");
            price=sc.nextDouble();
            if(price<0||price==0){
                System.out.println("enter valid price");
            }
            }while(price<=0);
            sc.nextLine();
        }
        void displayFood(){
            System.out.println("Food Name: " + name);
            System.out.println("Quantity: " + quantity);
            System.out.println("Price: " + (quantity * price));

        }
        void foodDetails(Scanner sc){
           System.out.println("enter item name");
           name = sc.nextLine();
           System.out.println("enter quantity available");
           quantity = sc.nextInt();
           System.out.println("enter price of 1 quantity");
           price = sc.nextDouble();
           sc.nextLine(); // clear buffer for next input
        }

    }

    // Hotel class
    static class Hotel {
        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<Food>foodMenu=new ArrayList<>();
        int customersNumber;

        Hotel() {
            System.out.println("Welcome to Hotel Management Project!");
        }
        void menu(Scanner sc){
           System.out.println("enter number of items in the menu");
           int items = sc.nextInt();
           sc.nextLine(); 
           for(int i=0;i<items;i++){
              Food f = new Food();
              f.foodDetails(sc);
              foodMenu.add(f);
           }
        }

        void displaymenu(){
            for(Food f:foodMenu){
               if(f.quantity > 0) { // only show items with quantity > 0
                  f.displayFood();
                }
            }
        }

        // Add rooms to hotel
        void addRooms(Scanner sc) {
            System.out.print("How many rooms are there in the hotel? ");
            int totalRooms = sc.nextInt();

            for (int i = 0; i < totalRooms; i++) {
                Room room = new Room();
                System.out.println("\nEnter details for Room " + (i + 1));
                room.roomInputs(sc);

                boolean duplicate = false;
                for (Room r : rooms) {
                    if (r.roomNumber == room.roomNumber || r.roomType == room.roomType) {
                        duplicate = true;
                        break;
                    }
                }

                if (duplicate) {
                    System.out.println("Room already exists! Enter again.");
                    i--;
                } else {
                    rooms.add(room);
                }
            }
        }

        void displayRooms() {
            System.out.println("\nAll Rooms in Hotel:");
            for (Room r : rooms) {
                r.display();
            }
        }

        void bookRoomForCustomers(Scanner sc) {
            System.out.print("\nHow many customers want to book rooms? ");
            int totalCustomers = sc.nextInt();
            customersNumber=totalCustomers;

            for (int i = 0; i < totalCustomers; i++) {
                System.out.println("\nBooking for Customer " + (i + 1));
                Customer customer = new Customer();
                customer.customerDetailsInput(sc, rooms);
                customer.customerDetails();
                customers.add(customer);
            }
        }

        void order(Scanner sc) {
             for (Customer c : customers) {
                if (c.food == 1) { // Customer wants food
                    System.out.println("\nFood Menu Available:");
                    displaymenu(); 
                  System.out.println("\nTaking food order for customer:");
                  c.customerDetails();
                  System.out.print("How many food items do you want to order? ");
                  int n = sc.nextInt();
                  sc.nextLine(); // consume newline
                  for (int i = 0; i < n; i++) {
                     System.out.println("Enter food name you want to order:");
                     String foodName = sc.nextLine();
                     // Find the food in hotel menu
                     Food menuFood = null;
                     for (Food fo : foodMenu) {
                          if (fo.name.equalsIgnoreCase(foodName)) {
                            menuFood = fo;
                            break;
                          }
                     }
                     if (menuFood == null) {
                       System.out.println("Sorry, this item is not available. Try something else.");
                       i--; // repeat this iteration
                       continue;
                     }
                     System.out.println("Enter quantity:");
                     int qty = sc.nextInt();
                     sc.nextLine(); // consume newline
                     if (qty > menuFood.quantity) {
                       System.out.println("Sorry, only " + menuFood.quantity + " available. Try less quantity.");
                       i--; // repeat this iteration
                       continue;
                     }
                     // Add the valid order to customer's orderedFood
                     Food ordered = new Food();
                     ordered.name = menuFood.name;
                     ordered.price = menuFood.price;
                     ordered.quantity = qty;
                     c.orderedFood.add(ordered);
                     // Reduce the available quantity in hotel menu
                     menuFood.quantity -= qty;
                    }
                }
            }
        }

        void bill(){
            for(Customer c:customers){
                if(c.food==1&&c.orderedFood.size()>0){
                    System.out.println("your bill");
                    System.out.println("custer details:\n");
                    c.customerDetails();
                    double totalbill=0;
                    for(Food f:c.orderedFood){
                        f.displayFood();
                        totalbill=totalbill+(f.quantity*f.price);
                    }
                    System.out.println("total bill:"+totalbill);
                }
            }
        }

        void showmenu(Scanner sc){
            int choice;
            System.out.println("which operation u want to do now select");
            System.out.println("1.Add rooms to hotel");
            System.out.println("2.veiw rooms");
            System.out.println("3.book room");
            System.out.println("4.view customers");
            System.out.println("5.bill");
            System.out.println("6.exit");
            while(true){
                 choice=sc.nextInt();
                switch(choice){
                    case 1:
                        System.out.println("add rooms to hotel");
                        addRooms(sc);
                        break;
                    case 2:
                        System.out.println("veiw rooms in hotel");
                        displayRooms();
                        break;
                    case 3:
                        System.out.println("book a room in hotel");
                        bookRoomForCustomers(sc);
                        break;
                    case 4:
                        for(Customer c:customers){
                            c.customerDetails();
                        }
                        break;
                    case 5:
                        bill();
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("invalid choice");
                        break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Hotel hotel = new Hotel();
        hotel.addRooms(sc);
        hotel.displayRooms();
        hotel.bookRoomForCustomers(sc);
        hotel.menu(sc);
        hotel.displaymenu();
        hotel.order(sc);
        hotel.showmenu(sc);

        sc.close();
        System.out.println("\nAll bookings completed successfully!");
    }
}
