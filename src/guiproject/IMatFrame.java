package guiproject;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.PopupMenu;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import se.chalmers.ait.dat215.project.CreditCard;
import se.chalmers.ait.dat215.project.Customer;
import se.chalmers.ait.dat215.project.IMatDataHandler;
import se.chalmers.ait.dat215.project.ProductCategory;
import se.chalmers.ait.dat215.project.ShoppingCart;
import se.chalmers.ait.dat215.project.ShoppingItem;

public class IMatFrame extends javax.swing.JFrame implements ActionListener {
    
    GUIProject gpCon;
    private CardLayout cl;
    private CardLayout cl2;
    DefaultListModel listModel = new DefaultListModel();

    private final AddressPanel addressPanel1 = new AddressPanel();
    
    private IMatDataHandler iMDH = IMatDataHandler.getInstance();
    private Customer customer = IMatDataHandler.getInstance().getCustomer();
    private CreditCard creditCard = IMatDataHandler.getInstance().getCreditCard();
    
    // Icons for the steps in the purchase.
    private final ImageIcon purchaseStep = new ImageIcon(getClass().getResource("/purchaseStep.png"));
    private final ImageIcon purchaseStepActive = new ImageIcon(getClass().getResource("/purchaseStepActive.png"));
    private ArrayList<JLabel> allCategoryArrayList = new ArrayList<JLabel>();
    private ArrayList<JLabel> headCategoryArrayList = new ArrayList<JLabel>();
    private ArrayList<JLabel> breadCategoryArrayList = new ArrayList<JLabel>();
    private ArrayList<JLabel> drinkCategoryArrayList = new ArrayList<JLabel>();
    private ArrayList<JLabel> fishCategoryArrayList = new ArrayList<JLabel>();
    private ArrayList<JLabel> fruitVegsCategoryArrayList = new ArrayList<JLabel>();
    private ArrayList<JLabel> spiceCategoryArrayList = new ArrayList<JLabel>();
    private ArrayList<JLabel> meatCategoryArrayList = new ArrayList<JLabel>();
    private ArrayList<JLabel> dairiesCategoryArrayList = new ArrayList<JLabel>();
    private ArrayList<JLabel> sweetsCategoryArrayList = new ArrayList<JLabel>();
    private ArrayList<JLabel> allSubCategoryArrayList = new ArrayList<JLabel>();
    private ArrayList<String> categoryStrings = new ArrayList<String>();
    public List<ProductCard> allProductCards = new ArrayList<ProductCard>();
    
    public void createAllProductCards(){
        orderHistoryList.setModel(listModel);
        gpCon.createAllProducts();
        for(int i = 0; i<gpCon.allProducts.size(); i++){
            allProductCards.add(new ProductCard(gpCon.allProducts.get(i), this));
        }
    }
    public void updateOrderListModel(){
        listModel.clear();
            for (int i = 0; i<gpCon.iMDH.getOrders().size(); i++){
//                System.out.println("uppdaterar orderlistmodel");
                listModel.addElement(gpCon.iMDH.getOrders().get(i).getDate());
//                System.out.println(listModel.get(i).toString());
            }
        orderHistoryList.setModel(listModel);
    }
        
    
    
    
    public void searchFieldDynSearch(){
    gpCon.doSearch(searchField.getText(), this);
        displayGroceries();
    }
    
    private void redrawCategories(){
        
        categoryPanel.removeAll();
        allCategoryArrayList.clear();
        for(int i = 0; i<headCategoryArrayList.size(); i++){
            allCategoryArrayList.add(headCategoryArrayList.get(i));
        }
        
    }
//    public void updateCartProd(List<CartProdObject> cpo){
//        System.out.print("updateCartProd :" + cpo.size());
//        for(ShoppingItem sc: gpCon.sc.getItems()){
//            for(CartProdObject cp: cpo){
//                sc.setAmount(cp.getSpinnerValue());
//            }
//        }
//        
//    }
    
    // DISPLAY METHODS ---------------------------------------------//
    
        public void displayCartPanel(List<CartProdObject> cpo){
        //lägger till Produkter i kassa-panelen  
        cartContentsPanel.removeAll();
        
        GridLayout layout = new GridLayout(gpCon.cpo.size()+4,1, 5,5);
        cartContentsPanel.setLayout(layout);
         
        for (CartProdObject cp : cpo) {
            cartContentsPanel.add(cp);
        }
        revalidate();
        repaint();
    }
        
    public void displayFavorites(){
//        System.out.println("gpcon.historyCards.size()" + gpCon.historyCards.size());
        
        myFavoritesDisplayPanel.removeAll();
        GridLayout layout = new GridLayout(0,3,5,5);
        myFavoritesDisplayPanel.setLayout(layout);
       
        
        for (ProductCard pc : gpCon.productCards) {
            myFavoritesDisplayPanel.add(pc);
            System.out.println(pc.getProduct() + " " + gpCon.iMDH.isFavorite(pc.getProduct()));
        }
        
        revalidate();
        repaint();
    }    
    private void displayHistory(){
//        System.out.println("gpcon.historyCards.size()" + gpCon.historyCards.size());
        GridLayout layout = new GridLayout(gpCon.historyCards.size()+4,1);
        historyItemShower.setLayout(layout);
        historyItemShower.removeAll();
        double cost = 0;
        int amount = 0;
        for (HistoryObjectCard hOC : gpCon.historyCards) {
            historyItemShower.add(hOC);
            cost = cost + hOC.getShoppingItem().getTotal();
            amount = (int) (amount + hOC.getShoppingItem().getAmount());
        }
        
        historyTotalCostPHLabel.setText(""+cost + " kr");
        historyNbrOfItemsPHLabel.setText(""+amount);
        
        
        
                
        cl.show(featurePanel, "historyViewPanel");
        revalidate();
        repaint();
    }

    private void displayGroceries(){
        // lägger till produkter i den allmäna panelen
        GridLayout layout1 = new GridLayout(0,3,5,5);
        itemShower.setLayout(layout1);
        
        itemShower.removeAll();
        if (gpCon.products.isEmpty()){
            JLabel noSearchResults = new JLabel("Inga sökresultat på " + searchField.getText());
            itemShower.add(noSearchResults);
        }else{
            for (ProductCard productCard : gpCon.productCards) {
            itemShower.add(productCard);
            }
        }

        cl.show(featurePanel, "searchResultPanel");
        revalidate();
        repaint();
    }
    
    public void displayCheckoutCart(ArrayList<CheckoutProductCard> cpoc){
        //lägger till produkter i checkout-panelen
        GridLayout layout2 = new GridLayout(cpoc.size()+4,1,5,5);
        prodPanel.setLayout(layout2);
        
        prodPanel.removeAll();
        if(cpoc.isEmpty()){
            JLabel noSearchResults = new JLabel("Varukorgen är tom");
            prodPanel.add(noSearchResults);
//            System.out.print("displayCheckoutCart empty!");
        }else{
            for(CheckoutProductCard cp: cpoc) {
            cp.setSize(40, prodPanel.getWidth());
            prodPanel.add(cp);  
//                System.out.println(cp.getHeight());
//                System.out.println(cp.getWidth());
            }
        }
        cl2.show(productPanel, "shoppingCartPanel");
        revalidate();
        repaint();
    }
    
    // END DISPLAY METHODS --------------------------------------------//
    
    public void changeActivePurchaseStep(String newCard, String oldCard){
        
        // Shows the new card as active in the progress bar.
        if(newCard == "shoppingCart"){
            checkoutShoppingCartLabel.setIcon(purchaseStepActive);
            checkoutShoppingCartLabel.setForeground(Color.WHITE);
            
        } else if(newCard == "personalInfoDelivery"){
            checkoutDeliveryLabel.setIcon(purchaseStepActive);
            checkoutDeliveryLabel.setForeground(Color.WHITE);
            
        } else if(newCard == "payment"){
            checkoutPaymentLabel.setIcon(purchaseStepActive);
            checkoutPaymentLabel.setForeground(Color.WHITE);
            
        } else if(newCard == "accept"){
            checkoutAcceptLabel.setIcon(purchaseStepActive);
            checkoutAcceptLabel.setForeground(Color.WHITE);
        }
        
        // Shows the old card as inactive in the progress bar.
        if(oldCard == "shoppingCart"){
            checkoutShoppingCartLabel.setIcon(purchaseStep);
            checkoutShoppingCartLabel.setForeground(Color.BLACK);
            
        } else if(oldCard == "personalInfoDelivery"){
            checkoutDeliveryLabel.setIcon(purchaseStep);
            checkoutDeliveryLabel.setForeground(Color.BLACK);
            
        } else if(oldCard == "payment"){
            checkoutPaymentLabel.setIcon(purchaseStep);
            checkoutPaymentLabel.setForeground(Color.BLACK);
            
        } else if(oldCard == "accept"){
            checkoutAcceptLabel.setIcon(purchaseStep);
            checkoutAcceptLabel.setForeground(Color.BLACK);
        }
    }
    
    // Resets the active purchase step in the progress bar to the first one.
    public void resetActivePurchaseStep(){
        checkoutShoppingCartLabel.setIcon(purchaseStepActive);
        checkoutShoppingCartLabel.setForeground(Color.WHITE);
        checkoutDeliveryLabel.setIcon(purchaseStep);
        checkoutDeliveryLabel.setForeground(Color.BLACK);
        checkoutPaymentLabel.setIcon(purchaseStep);
        checkoutPaymentLabel.setForeground(Color.BLACK);
        checkoutAcceptLabel.setIcon(purchaseStep);
        checkoutAcceptLabel.setForeground(Color.BLACK);
    }
    
    public void setValuesMyAccount(){
        // Sets values for text fields in "Personal information"
        firstNameTextField.setText(customer.getFirstName());
        lastNameTextField.setText(customer.getLastName());
        address1TextField.setText(customer.getAddress());
        postCodeTextField.setText(customer.getPostCode());
        emailTextField.setText(customer.getEmail());
        phoneNumberTextField.setText(customer.getPhoneNumber());
        
        // Sets values for text fields and comboboxes in "Payment"
        cardNumberTextField.setText(creditCard.getCardNumber());
        validMonthComboBox.setSelectedIndex(creditCard.getValidMonth());
        
        if (creditCard.getValidYear() < 2015){
            creditCard.setValidYear(2015);
        } else if (creditCard.getValidYear() > 2020){
            creditCard.setValidYear(2020);
        }
        
        validYearComboBox.setSelectedIndex(creditCard.getValidYear() - 2015);
        verificationCodeTextField.setText(creditCard.getVerificationCode() + "");
        cardHolderTextField.setText(creditCard.getHoldersName());
        
        // Sets values for combo- and checkboxes in "Delivery"
        // Not implemented though
    }
    
    public void setDeliveryInfoCheckout(){
        checkoutFirstNameTF.setText(customer.getFirstName());
        checkoutLastNameTF.setText(customer.getLastName());
        checkoutAddressTF.setText(customer.getAddress());
        checkoutPostCodeTF.setText(customer.getPostCode());
        checkoutEmailTF.setText(customer.getEmail());
        checkoutPhoneNumberTF.setText(customer.getPhoneNumber());
    }
    
    public void setPaymentInfoCheckout(){
        checkoutCardNumberTF.setText(creditCard.getCardNumber());
        //checkoutCardTypeCB
        checkoutValidMonthCB.setSelectedIndex(creditCard.getValidMonth());
        
//        if (iMDH.isFirstRun()){
//            creditCard.setValidYear(1);
//        }
        
        if (creditCard.getValidYear() < 2015){
            creditCard.setValidYear(2015);
        } else if (creditCard.getValidYear() > 2020){
            creditCard.setValidYear(2020);
        }
        
        checkoutValidYearCB.setSelectedIndex(creditCard.getValidYear() - 2015);
        checkoutVerificationCodeTF.setText(creditCard.getVerificationCode() + "");
        checkoutCardHolderTF.setText(creditCard.getHoldersName());
    }
    
    public void setDeliveryAdress(){
        
        acceptNameLabel.setText(checkoutFirstNameTF.getText() + " " + checkoutLastNameTF.getText());
        acceptAddressLabel.setText(checkoutAddressTF.getText());
        acceptPostCodeLabel.setText(checkoutPostCodeTF.getText());
        acceptDeliveryTime.setText("Leveranstid: " + deliveryTimeComboBox1.getSelectedItem().toString());
        acceptCardTypeLabel.setText(checkoutCardTypeCB.getSelectedItem().toString());
        
        if (checkoutCardNumberTF.getText().length() >= 10){
        acceptCardNumberLabel.setText("****" + checkoutCardNumberTF.getText().substring(10));
        }
        acceptValidMonthYearLabel.setText("Utgångsdatum: " + checkoutValidMonthCB.getSelectedItem().toString() + "/" + checkoutValidYearCB.getSelectedItem());
        acceptCardHolderLabel.setText("Kortinnehavare: " + checkoutCardHolderTF.getText());
           
    }
    public void refreshStartPage(){
        startLatestOrdersDisplayPanel.removeAll();
        if (iMDH.getOrders().size() >0){
            for (int i = 1; gpCon.iMDH.getOrders().size()-i>0 && i<6; i++){
                gpCon.getHistory(this, gpCon.iMDH.getOrders().size()-i);
                for (HistoryObjectCard hOC : gpCon.historyCards) {
                    startLatestOrdersDisplayPanel.add(new ProductCard(hOC.getShoppingItem().getProduct(), this));
                }
            }
        } else {
            startLatestOrdersDisplayPanel.add(new JLabel("Du har inte gjort någon beställning än!"));
        
        }
        repaint();
        revalidate();
    }
    
    public IMatFrame() {
        initComponents();
        this.gpCon = new GUIProject();
//        gpCon.iMDH.reset();
        cl = (CardLayout)featurePanel.getLayout();
        cl2 = (CardLayout)productPanel.getLayout();
       
        deliveryPanel.add(addressPanel1);
        createAllProductCards();
        
        updateOrderListModel();
        startLatestOrdersDisplayPanel.setLayout(new GridLayout(1, 20, 10, 1));
        
        refreshStartPage();
        
        revalidate();
        repaint();
    
        JLabel headCategoryBread = new JLabel("Bröd och torrvaror");
        headCategoryArrayList.add(headCategoryBread);

            JLabel subCategoryBreadLabel = new JLabel("     Bröd");
            JLabel subCategoryFlourSugarSaltLabel = new JLabel("     Mjöl, socker och salt");
            JLabel subCategoryPastaLabel = new JLabel("     Pasta");
            JLabel subCategoryPotatoRiceLabel = new JLabel("     Potatis och ris");

        breadCategoryArrayList.add(subCategoryBreadLabel);
        breadCategoryArrayList.add(subCategoryFlourSugarSaltLabel);
        breadCategoryArrayList.add(subCategoryPastaLabel);
        breadCategoryArrayList.add(subCategoryPotatoRiceLabel);

        for(int i = 0; i<breadCategoryArrayList.size(); i++){
            breadCategoryArrayList.get(i).setBackground(Color.white);
            breadCategoryArrayList.get(i).setOpaque(true);;
        }

        JLabel headCategoryDrinks = new JLabel("Drycker");
        headCategoryArrayList.add(headCategoryDrinks);

            JLabel subCategoryHotDrinksLabel = new JLabel("     Varma drycker");
            JLabel subCategoryColdDrinksLabel = new JLabel("     Kalla drycker");

        drinkCategoryArrayList.add(subCategoryHotDrinksLabel);
        drinkCategoryArrayList.add(subCategoryColdDrinksLabel);

        for(int i = 0; i<drinkCategoryArrayList.size(); i++){
            drinkCategoryArrayList.get(i).setBackground(Color.white);
            drinkCategoryArrayList.get(i).setOpaque(true);
        }

        JLabel headCategoryFish = new JLabel("Fisk");
        headCategoryArrayList.add(headCategoryFish);

            JLabel subCategoryFishLabel = new JLabel("Fisk");

        fishCategoryArrayList.add(subCategoryFishLabel);

        for(int i = 0; i<fishCategoryArrayList.size(); i++){
            fishCategoryArrayList.get(i).setBackground(Color.white);
            fishCategoryArrayList.get(i).setOpaque(true);
        }

        JLabel headCategoryFruitsAndVegs = new JLabel("Frukt och grönt");
        headCategoryArrayList.add(headCategoryFruitsAndVegs);


            JLabel subCategoryPodLabel = new JLabel("     Baljväxter");
            JLabel subCategoryBerryLabel = new JLabel("     Bär");
            JLabel subCategoryCitrusFruitLabel = new JLabel("     Citrusfrukter");
            JLabel subCategoryExoticFruitLabel = new JLabel("     Exotiska frukter");
            JLabel subCategoryVegetableFruitLabel = new JLabel("     Grönsaksfrukter");
            JLabel subCategoryCabbageLabel = new JLabel("     Kål");
            JLabel subCategoryMelonsLabel = new JLabel("     Meloner");
            JLabel subCategoryNutsAndSeedsLabel = new JLabel("     Nötter och frön");
            JLabel subCategoryRootVegetableLabel = new JLabel("     Rotfrukter");
            JLabel subCategoryFruitLabel = new JLabel("     Stenfrukter");
            

        fruitVegsCategoryArrayList.add(subCategoryPodLabel);
        fruitVegsCategoryArrayList.add(subCategoryBerryLabel);
        fruitVegsCategoryArrayList.add(subCategoryCitrusFruitLabel);
        fruitVegsCategoryArrayList.add(subCategoryExoticFruitLabel);
        fruitVegsCategoryArrayList.add(subCategoryVegetableFruitLabel);
        fruitVegsCategoryArrayList.add(subCategoryCabbageLabel);
        fruitVegsCategoryArrayList.add(subCategoryMelonsLabel);
        fruitVegsCategoryArrayList.add(subCategoryNutsAndSeedsLabel);
        fruitVegsCategoryArrayList.add(subCategoryRootVegetableLabel);
        fruitVegsCategoryArrayList.add(subCategoryFruitLabel);
        

        for(int i = 0; i<fruitVegsCategoryArrayList.size(); i++){
            fruitVegsCategoryArrayList.get(i).setBackground(Color.white);
            fruitVegsCategoryArrayList.get(i).setOpaque(true);
        }

        JLabel headCategorySpice = new JLabel("Kryddor");
        headCategoryArrayList.add(headCategorySpice);

            JLabel subCategoryHerbLabel = new JLabel("     Örter");
        spiceCategoryArrayList.add(subCategoryHerbLabel);

        for(int i = 0; i<spiceCategoryArrayList.size(); i++){
            spiceCategoryArrayList.get(i).setBackground(Color.white);
            spiceCategoryArrayList.get(i).setOpaque(true);
        }

        JLabel headCategoryMeat = new JLabel("Kött");
        headCategoryArrayList.add(headCategoryMeat);

            JLabel subCategoryMeatLabel = new JLabel("Kött");
        meatCategoryArrayList.add(subCategoryMeatLabel);

        for(int i = 0; i<meatCategoryArrayList.size(); i++){
            meatCategoryArrayList.get(i).setBackground(Color.white);
            meatCategoryArrayList.get(i).setOpaque(true);
        }

        JLabel headCategoryDairies = new JLabel("Mejeriprodukter");
        headCategoryArrayList.add(headCategoryDairies);

            JLabel category12Label = new JLabel("Mejeriprodukter");
        dairiesCategoryArrayList.add(category12Label);

        for(int i = 0; i<dairiesCategoryArrayList.size(); i++){
            dairiesCategoryArrayList.get(i).setBackground(Color.white);
            dairiesCategoryArrayList.get(i).setOpaque(true);
        }

        JLabel headCategorySweets = new JLabel("Sötsaker");
        headCategoryArrayList.add(headCategorySweets);

        JLabel category20Label = new JLabel("Sötsaker");
        sweetsCategoryArrayList.add(category20Label);

        for(int i = 0; i<sweetsCategoryArrayList.size(); i++){
            sweetsCategoryArrayList.get(i).setBackground(Color.white);
            sweetsCategoryArrayList.get(i).setOpaque(true);
        }

        allSubCategoryArrayList.addAll(breadCategoryArrayList);
        allSubCategoryArrayList.addAll(drinkCategoryArrayList);
        allSubCategoryArrayList.addAll(fishCategoryArrayList);
        allSubCategoryArrayList.addAll(fruitVegsCategoryArrayList);
        allSubCategoryArrayList.addAll(spiceCategoryArrayList);
        allSubCategoryArrayList.addAll(meatCategoryArrayList);
        allSubCategoryArrayList.addAll(dairiesCategoryArrayList);
        allSubCategoryArrayList.addAll(sweetsCategoryArrayList);


        for (int i = 0; i<headCategoryArrayList.size(); i++){
            allCategoryArrayList.add(headCategoryArrayList.get(i));
        }

                    for (int i = 0; i<allCategoryArrayList.size(); i++){
                        allCategoryArrayList.get(i).addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {

                                categoryLabelMouseClicked(evt);
                            }
                        });
                    }

                    for (int i = 0; i<allSubCategoryArrayList.size(); i++){

                        allSubCategoryArrayList.get(i).addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {

                                categoryLabelMouseClicked(evt);
                            }
                        });

                    }
                    placeCategories();
                    revalidate();
    }


    private void placeCategories(){

        for (int i = 0; i<allCategoryArrayList.size(); i++){
                categoryPanel.add(allCategoryArrayList.get(i));
                allCategoryArrayList.get(i).setHorizontalAlignment(SwingConstants.LEFT);
                allCategoryArrayList.get(i).setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        }
    }

    public void actionPerformed(ActionEvent e) {

    }
    
    public void addRecommendations(){
        boolean match = false;
        recProdsInsideScroll.removeAll();
        GridLayout layout = new GridLayout(30,1, 3,3);
        recProdsInsideScroll.setLayout(layout);
        
        for (int i = 0; i < gpCon.iMDH.favorites().size(); i++){
            for (int j = 0; j < gpCon.sc.getItems().size(); j++){
                if (gpCon.iMDH.favorites().get(i) == gpCon.sc.getItems().get(j).getProduct()){
                    match = true;
                }
            }
            if (match == false){
//                System.out.println("lägger till " + gpCon.iMDH.favorites().get(i).toString() + " till glömtlista.");

                recProdsInsideScroll.add(new RecProductCard(gpCon.iMDH.favorites().get(i), this));
            }
            match = false;
        }
        

      
        revalidate();
        repaint();
        
        
    }
    public void setCashierPriceLabel(String s){
        priceLabel.setText(s + " kr");
    }
     public void setCartLayout(GridLayout lo){
        cartContentsPanel.setLayout(lo);
    }        
    public void setLabelCartNbr(String s){
        cartPanelNbrOfItemsLabel.setText(s + " st");
    }
    public void setLabelCartCost(String s){
        cartPanelTotalCostLabel.setText(s + " kr");
    }
            
    private void categoryLabelMouseClicked(java.awt.event.MouseEvent evt) {   

        JLabel tempLabel1 = (JLabel)evt.getSource();

        categoryStrings.clear();
        System.out.println("Labeltext: " + tempLabel1.getText());
        switch(tempLabel1.getText()){
            case "Bröd och torrvaror": 
                redrawCategories();
                
                categoryStrings.add("BREAD");
                categoryStrings.add("FLOUR_SUGAR_SALT");
                categoryStrings.add("PASTA");
                categoryStrings.add("POTATO_RICE");
                
                for (int i = 0; i<categoryStrings.size(); i++){
//                    System.out.println(categoryStrings.get(i).toString());
                }
                
                gpCon.listCatProds(categoryStrings, this);
                
                displayGroceries();
                
                for (int i = 0; i<breadCategoryArrayList.size(); i++){
                    allCategoryArrayList.add(1+i, breadCategoryArrayList.get(i));
                }
                
                break;
            case "Drycker": 
                redrawCategories();
                categoryStrings.add("HOT_DRINKS");
                categoryStrings.add("COLD_DRINKS");
                                
                for (int i = 0; i<categoryStrings.size(); i++){
//                    System.out.println(categoryStrings.get(i).toString());
                }
                
                gpCon.listCatProds(categoryStrings, this);
                
                displayGroceries();
                
                for (int i = 0; i<drinkCategoryArrayList.size(); i++){
                    
                    allCategoryArrayList.add(2+i, drinkCategoryArrayList.get(i));
                }
                
                break;
            case "Frukt och grönt": 
                redrawCategories();
                categoryStrings.add("POD");
                categoryStrings.add("BERRY");
                categoryStrings.add("CITRUS_FRUIT");
                categoryStrings.add("EXOTIC_FRUIT");
                categoryStrings.add("FRUIT");
                categoryStrings.add("VEGETABLE_FRUIT");
                categoryStrings.add("CABBAGE");
                categoryStrings.add("MELONS");
                categoryStrings.add("NUTS_AND_SEEDS");
                categoryStrings.add("ROOT_VEGETABLE");
                
                
                for (int i = 0; i<categoryStrings.size(); i++){
//                    System.out.println(categoryStrings.get(i).toString());
                }
                
                gpCon.listCatProds(categoryStrings, this);
                
                displayGroceries();
                
                for (int i = 0; i<fruitVegsCategoryArrayList.size(); i++){
                    
                    allCategoryArrayList.add(4+i, fruitVegsCategoryArrayList.get(i));
                }
                
                break;
            case "Kryddor":
                redrawCategories();
                
                categoryStrings.add("HERB");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                
                for (int i = 0; i<spiceCategoryArrayList.size(); i++){
                    
                    allCategoryArrayList.add(5+i, spiceCategoryArrayList.get(i));
                }
                
                break;
            case "     Baljväxter":
                categoryStrings.add("POD");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "     Bröd":
                categoryStrings.add("BREAD");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "     Bär":
                categoryStrings.add("BERRY");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "     Citrusfrukter":
                categoryStrings.add("CITRUS_FRUIT");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "     Varma drycker":
                categoryStrings.add("HOT_DRINKS");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "     Kalla drycker":
                categoryStrings.add("COLD_DRINKS");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "     Exotiska frukter":
                categoryStrings.add("EXOTIC_FRUIT");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "Fisk":
                categoryStrings.add("FISH");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "     Grönsaksfrukter":
                categoryStrings.add("VEGETABLE_FRUIT");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "     Kål":
                categoryStrings.add("CABBAGE");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "Kött":
                categoryStrings.add("MEAT");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "Mejeriprodukter":
                categoryStrings.add("DAIRIES");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "     Meloner":
                categoryStrings.add("MELONS");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "     Mjöl, socker och salt":
                categoryStrings.add("FLOUR_SUGAR_SALT");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "     Nötter och frön":
                categoryStrings.add("NUTS_AND_SEEDS");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "     Pasta":
                categoryStrings.add("PASTA");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "     Potatis och ris":
                categoryStrings.add("POTATO_RICE");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "     Rotfrukter":
                categoryStrings.add("ROOT_VEGETABLE");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "     Stenfrukter":
                categoryStrings.add("FRUIT");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "Sötsaker":
                categoryStrings.add("SWEET");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "     Örter":
                categoryStrings.add("HERB");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
                    default:
                break;
                
        }

        placeCategories();
        revalidate();
        
    }                
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        leftPanel = new javax.swing.JPanel();
        leftUpperPanel = new javax.swing.JPanel();
        searchField = new javax.swing.JTextField();
        searchFieldIcon = new javax.swing.JLabel();
        leftPanelSpecials = new javax.swing.JPanel();
        lpFavoritesLabel = new javax.swing.JLabel();
        lpDealsLabel = new javax.swing.JLabel();
        categoryPanel = new javax.swing.JPanel();
        searchOptSeparator = new javax.swing.JSeparator();
        optCatSeparator = new javax.swing.JSeparator();
        topPanel = new javax.swing.JPanel();
        tpMyAccountLabel = new javax.swing.JLabel();
        accountImage = new javax.swing.JLabel();
        myShoppingBagsBtn = new javax.swing.JButton();
        recipesBtn = new javax.swing.JButton();
        historyBtn = new javax.swing.JButton();
        logoPanel = new javax.swing.JPanel();
        iMatLabel = new javax.swing.JLabel();
        featurePanel = new javax.swing.JPanel();
        startViewPanel = new javax.swing.JPanel();
        recentlyBoughtLabel = new javax.swing.JLabel();
        popularLabel = new javax.swing.JLabel();
        dealsLabel = new javax.swing.JLabel();
        separator6 = new javax.swing.JLabel();
        separator7 = new javax.swing.JLabel();
        separator9 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        startLatestOrdersDisplayPanel = new javax.swing.JPanel();
        fpDealsLabel1 = new javax.swing.JLabel();
        myShoppingBagsViewPanel = new javax.swing.JPanel();
        fpMyShoppingBagsLabel = new javax.swing.JLabel();
        myFavoritesViewPanel = new javax.swing.JPanel();
        fpMyFavoritesLabel = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        myFavoritesDisplayPanel = new javax.swing.JPanel();
        recipesViewPanel = new javax.swing.JPanel();
        fpRecipesLabel = new javax.swing.JLabel();
        dealsViewPanel = new javax.swing.JPanel();
        fpDealsLabel = new javax.swing.JLabel();
        historyViewPanel = new javax.swing.JPanel();
        fpHistoryLabel = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        historyItemShower = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        orderHistoryList = new javax.swing.JList();
        addHistoryToCart = new javax.swing.JButton();
        historyNbrOfItemsLabel = new javax.swing.JLabel();
        historyNbrOfItemsPHLabel = new javax.swing.JLabel();
        historyTotalCostlabel = new javax.swing.JLabel();
        historyTotalCostPHLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        categoryFeaturePanel = new javax.swing.JPanel();
        categoryFeatureLabel = new javax.swing.JLabel();
        searchResultPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        itemShower = new javax.swing.JPanel();
        myAccountPanel = new javax.swing.JPanel();
        myAccountContentPanel = new javax.swing.JPanel();
        personalInfoPanel = new javax.swing.JPanel();
        personalInfoLabel = new javax.swing.JLabel();
        firstNameLabel = new javax.swing.JLabel();
        firstNameTextField = new javax.swing.JTextField();
        lastNameTextField = new javax.swing.JTextField();
        lastNameLabel = new javax.swing.JLabel();
        address1TextField = new javax.swing.JTextField();
        adress1Label = new javax.swing.JLabel();
        address2TextField = new javax.swing.JTextField();
        adress2Label = new javax.swing.JLabel();
        postCodeLabel = new javax.swing.JLabel();
        postCodeTextField = new javax.swing.JTextField();
        cityLabel = new javax.swing.JLabel();
        cityTextField = new javax.swing.JTextField();
        emailLabel = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        separator1 = new javax.swing.JLabel();
        phoneNumberTextField = new javax.swing.JTextField();
        phoneNumberLabel = new javax.swing.JLabel();
        paymentInfoPanel = new javax.swing.JPanel();
        paymentInfoLabel = new javax.swing.JLabel();
        cardNumberLabel = new javax.swing.JLabel();
        cardNumberTextField = new javax.swing.JTextField();
        cardTypeComboBox = new javax.swing.JComboBox();
        cardTypeLabel = new javax.swing.JLabel();
        expirationDateLabel = new javax.swing.JLabel();
        validMonthComboBox = new javax.swing.JComboBox();
        expDateSlashLabel = new javax.swing.JLabel();
        validYearComboBox = new javax.swing.JComboBox();
        verificationCodeLabel = new javax.swing.JLabel();
        verificationCodeTextField = new javax.swing.JTextField();
        cardHolderNameLabel = new javax.swing.JLabel();
        cardHolderTextField = new javax.swing.JTextField();
        separator2 = new javax.swing.JLabel();
        deliveryPanel = new javax.swing.JPanel();
        personalInfoLabel1 = new javax.swing.JLabel();
        deliveryDateLabel = new javax.swing.JLabel();
        deliveryTimeComboBox = new javax.swing.JComboBox();
        deliveryDayComboBox = new javax.swing.JComboBox();
        deliverToMyAdressPanel = new javax.swing.JPanel();
        deliverToMyAddressLabel = new javax.swing.JLabel();
        deliveryCheckBox = new javax.swing.JCheckBox();
        separator3 = new javax.swing.JLabel();
        addressPanel = new javax.swing.JPanel();
        adress1Label1 = new javax.swing.JLabel();
        address1TextField1 = new javax.swing.JTextField();
        adress2TextField1 = new javax.swing.JTextField();
        adress2Label1 = new javax.swing.JLabel();
        codeLabel1 = new javax.swing.JLabel();
        postCodeTextField1 = new javax.swing.JTextField();
        cityTextField1 = new javax.swing.JTextField();
        cityLabel1 = new javax.swing.JLabel();
        saveButton2 = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        myAccountTopPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        titleNameLabel = new javax.swing.JLabel();
        checkoutPanel = new javax.swing.JPanel();
        progressPanel = new javax.swing.JPanel();
        checkoutShoppingCartLabel = new javax.swing.JLabel();
        checkoutDeliveryLabel = new javax.swing.JLabel();
        checkoutPaymentLabel = new javax.swing.JLabel();
        checkoutAcceptLabel = new javax.swing.JLabel();
        arrow1Label = new javax.swing.JLabel();
        arrow2Label = new javax.swing.JLabel();
        arrow3Label = new javax.swing.JLabel();
        productPanel = new javax.swing.JPanel();
        shoppingCartPanel = new javax.swing.JPanel();
        productScrollPane = new javax.swing.JScrollPane();
        prodPanel = new javax.swing.JPanel();
        nextStepButtonToPersonalInfo = new javax.swing.JButton();
        totalPriceLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        krLabel = new javax.swing.JLabel();
        recommendedProductsPanel = new javax.swing.JPanel();
        recommendedProductsLabel = new javax.swing.JLabel();
        recommendedProductsScrollPane = new javax.swing.JScrollPane();
        recProdsInsideScroll = new javax.swing.JPanel();
        checkPersonalInfoPanel = new javax.swing.JPanel();
        personalInfoPanel1 = new javax.swing.JPanel();
        personalInfoLabel2 = new javax.swing.JLabel();
        firstNameLabel1 = new javax.swing.JLabel();
        checkoutFirstNameTF = new javax.swing.JTextField();
        checkoutLastNameTF = new javax.swing.JTextField();
        lastNameLabel1 = new javax.swing.JLabel();
        checkoutAddressTF = new javax.swing.JTextField();
        adress1Label2 = new javax.swing.JLabel();
        address2TextField1 = new javax.swing.JTextField();
        adress2Label2 = new javax.swing.JLabel();
        postCodeLabel1 = new javax.swing.JLabel();
        checkoutPostCodeTF = new javax.swing.JTextField();
        cityLabel2 = new javax.swing.JLabel();
        cityTextField2 = new javax.swing.JTextField();
        emailLabel1 = new javax.swing.JLabel();
        checkoutEmailTF = new javax.swing.JTextField();
        separator4 = new javax.swing.JLabel();
        checkoutPhoneNumberTF = new javax.swing.JTextField();
        phoneNumberLabel1 = new javax.swing.JLabel();
        deliveryPanel1 = new javax.swing.JPanel();
        personalInfoLabel3 = new javax.swing.JLabel();
        deliveryDateLabel1 = new javax.swing.JLabel();
        deliveryTimeComboBox1 = new javax.swing.JComboBox();
        deliveryDayComboBox1 = new javax.swing.JComboBox();
        deliverToMyAdressPanel1 = new javax.swing.JPanel();
        deliverToMyAddressLabel1 = new javax.swing.JLabel();
        deliveryCheckBox1 = new javax.swing.JCheckBox();
        separator5 = new javax.swing.JLabel();
        addressPanel2 = new javax.swing.JPanel();
        adress1Label3 = new javax.swing.JLabel();
        checkoutOtherAddressTF = new javax.swing.JTextField();
        adress2TextField2 = new javax.swing.JTextField();
        adress2Label3 = new javax.swing.JLabel();
        codeLabel2 = new javax.swing.JLabel();
        checkoutOtherPostCodeTF = new javax.swing.JTextField();
        cityTextField3 = new javax.swing.JTextField();
        cityLabel3 = new javax.swing.JLabel();
        backButtonToShoppingCart = new javax.swing.JButton();
        nextStepButtonToPayment = new javax.swing.JButton();
        saveButton1 = new javax.swing.JButton();
        checkPaymentPanel = new javax.swing.JPanel();
        backButtonToPersonalInfo = new javax.swing.JButton();
        nextStepButtonToAccept = new javax.swing.JButton();
        paymentInfoPanel1 = new javax.swing.JPanel();
        paymentInfoLabel1 = new javax.swing.JLabel();
        cardNumberLabel1 = new javax.swing.JLabel();
        checkoutCardNumberTF = new javax.swing.JTextField();
        checkoutCardTypeCB = new javax.swing.JComboBox();
        cardTypeLabel1 = new javax.swing.JLabel();
        expirationDateLabel1 = new javax.swing.JLabel();
        checkoutValidMonthCB = new javax.swing.JComboBox();
        expDateSlashLabel1 = new javax.swing.JLabel();
        checkoutValidYearCB = new javax.swing.JComboBox();
        verificationCodeLabel1 = new javax.swing.JLabel();
        checkoutVerificationCodeTF = new javax.swing.JTextField();
        cardHolderNameLabel1 = new javax.swing.JLabel();
        checkoutCardHolderTF = new javax.swing.JTextField();
        separator8 = new javax.swing.JLabel();
        acceptPanel = new javax.swing.JPanel();
        backButtonToPayment = new javax.swing.JButton();
        acceptPurchaseButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        acceptNameLabel = new javax.swing.JLabel();
        acceptAddressLabel = new javax.swing.JLabel();
        acceptPostCodeLabel = new javax.swing.JLabel();
        acceptDeliveryLabel = new javax.swing.JLabel();
        acceptShoppingCartLabel = new javax.swing.JLabel();
        acceptSeparator1 = new javax.swing.JLabel();
        acceptSeparator3 = new javax.swing.JLabel();
        acceptPaymentLabel = new javax.swing.JLabel();
        acceptSeparator4 = new javax.swing.JLabel();
        acceptCardNumberLabel = new javax.swing.JLabel();
        acceptCardTypeLabel = new javax.swing.JLabel();
        acceptValidMonthYearLabel = new javax.swing.JLabel();
        acceptCardHolderLabel = new javax.swing.JLabel();
        acceptDeliveryTime = new javax.swing.JLabel();
        endOfCheckoutPanel = new javax.swing.JPanel();
        thanksLabel = new javax.swing.JLabel();
        backToStoreButton = new javax.swing.JButton();
        cartPanel = new javax.swing.JPanel();
        goToCashierBtn = new javax.swing.JButton();
        cartPanelScrollPane = new javax.swing.JScrollPane();
        cartContentsPanel = new javax.swing.JPanel();
        cartPanelNbrOfItemsLabel = new javax.swing.JLabel();
        tpCartContentsNumber = new javax.swing.JLabel();
        cartPanelTotalCostLabel = new javax.swing.JLabel();
        tpCartSumLabel = new javax.swing.JLabel();
        cartImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(215, 173, 173));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        leftPanel.setBackground(new java.awt.Color(215, 173, 173));
        leftPanel.setToolTipText("");

        leftUpperPanel.setBackground(new java.awt.Color(255, 248, 248));
        leftUpperPanel.setLayout(new java.awt.BorderLayout());

        searchField.setToolTipText("Skriv här för att söka");
        searchField.setBorder(null);
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                searchFieldDynSearch();
            }
            public void removeUpdate(DocumentEvent e) {
                searchFieldDynSearch();
            }
            public void insertUpdate(DocumentEvent e) {
                searchFieldDynSearch();
            }
        });
        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });
        leftUpperPanel.add(searchField, java.awt.BorderLayout.CENTER);

        searchFieldIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iMatSearch3.png"))); // NOI18N
        leftUpperPanel.add(searchFieldIcon, java.awt.BorderLayout.LINE_END);

        leftPanelSpecials.setBackground(new java.awt.Color(215, 173, 173));
        leftPanelSpecials.setLayout(new java.awt.GridLayout(2, 1));

        lpFavoritesLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lpFavoritesLabel.setText("Favoriter");
        lpFavoritesLabel.setToolTipText("Visa favoriter");
        lpFavoritesLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lpFavoritesLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lpFavoritesLabelMouseClicked(evt);
            }
        });
        leftPanelSpecials.add(lpFavoritesLabel);

        lpDealsLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lpDealsLabel.setText("Erbjudanden");
        lpDealsLabel.setToolTipText("Visa erbjudanden");
        lpDealsLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lpDealsLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lpDealsLabelMouseClicked(evt);
            }
        });
        leftPanelSpecials.add(lpDealsLabel);

        categoryPanel.setBackground(new java.awt.Color(215, 173, 173));
        categoryPanel.setLayout(new java.awt.GridLayout(30, 1));

        searchOptSeparator.setForeground(new java.awt.Color(179, 62, 62));

        optCatSeparator.setForeground(new java.awt.Color(179, 62, 62));
        optCatSeparator.setToolTipText("");

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(leftUpperPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(leftPanelLayout.createSequentialGroup()
                        .addComponent(categoryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addComponent(leftPanelSpecials, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)))
            .addComponent(optCatSeparator, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(searchOptSeparator, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addComponent(leftUpperPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(searchOptSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(leftPanelSpecials, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(optCatSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(categoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200))
        );

        getContentPane().add(leftPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 83, -1, 605));

        topPanel.setBackground(new java.awt.Color(179, 62, 62));

        tpMyAccountLabel.setForeground(new java.awt.Color(255, 255, 255));
        tpMyAccountLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tpMyAccountLabel.setText("Mitt konto");
        tpMyAccountLabel.setToolTipText("Visa mitt konto");
        tpMyAccountLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tpMyAccountLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tpMyAccountLabelMouseClicked(evt);
            }
        });

        accountImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iMatAvatar2.png"))); // NOI18N
        accountImage.setToolTipText("Visa mitt konto");
        accountImage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        accountImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                accountImageMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                accountImageMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                accountImageMouseExited(evt);
            }
        });

        myShoppingBagsBtn.setBackground(new java.awt.Color(215, 173, 173));
        myShoppingBagsBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        myShoppingBagsBtn.setText("Mina matkassar");
        myShoppingBagsBtn.setToolTipText("Se mina matkassar");
        myShoppingBagsBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        myShoppingBagsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myShoppingBagsBtnActionPerformed(evt);
            }
        });

        recipesBtn.setBackground(new java.awt.Color(215, 173, 173));
        recipesBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        recipesBtn.setText("Recept");
        recipesBtn.setToolTipText("Hitta recept");
        recipesBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        recipesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recipesBtnActionPerformed(evt);
            }
        });

        historyBtn.setBackground(new java.awt.Color(215, 173, 173));
        historyBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        historyBtn.setText("Historik");
        historyBtn.setToolTipText("Se information om tidigare beställningar");
        historyBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        historyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyBtnActionPerformed(evt);
            }
        });

        logoPanel.setBackground(new java.awt.Color(179, 62, 62));
        logoPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoPanelMouseClicked(evt);
            }
        });

        iMatLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        iMatLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/logga.png"))); // NOI18N
        iMatLabel.setToolTipText("Gå till startsidan");
        iMatLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iMatLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iMatLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout logoPanelLayout = new javax.swing.GroupLayout(logoPanel);
        logoPanel.setLayout(logoPanelLayout);
        logoPanelLayout.setHorizontalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iMatLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        logoPanelLayout.setVerticalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iMatLabel)
        );

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(myShoppingBagsBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(recipesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(historyBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 701, Short.MAX_VALUE)
                .addComponent(accountImage)
                .addGap(18, 18, 18)
                .addComponent(tpMyAccountLabel)
                .addGap(47, 47, 47))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(logoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(myShoppingBagsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(recipesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(historyBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, topPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(accountImage)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, topPanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(tpMyAccountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(49, 49, 49))
        );

        getContentPane().add(topPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1310, 75));

        featurePanel.setBackground(new java.awt.Color(255, 248, 248));
        featurePanel.setLayout(new java.awt.CardLayout());

        startViewPanel.setBackground(new java.awt.Color(255, 248, 248));

        recentlyBoughtLabel.setFont(new java.awt.Font("Gautami", 0, 20)); // NOI18N
        recentlyBoughtLabel.setForeground(new java.awt.Color(51, 51, 51));
        recentlyBoughtLabel.setText("Mina senaste inköp");
        recentlyBoughtLabel.setToolTipText("");
        recentlyBoughtLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        popularLabel.setFont(new java.awt.Font("Gautami", 0, 20)); // NOI18N
        popularLabel.setForeground(new java.awt.Color(51, 51, 51));
        popularLabel.setText("Populärt");
        popularLabel.setToolTipText("");
        popularLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        dealsLabel.setFont(new java.awt.Font("Gautami", 0, 20)); // NOI18N
        dealsLabel.setForeground(new java.awt.Color(51, 51, 51));
        dealsLabel.setText("Erbjudanden");
        dealsLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        separator6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redSeparator.png"))); // NOI18N

        separator7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redSeparator.png"))); // NOI18N

        separator9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redSeparator.png"))); // NOI18N

        javax.swing.GroupLayout startLatestOrdersDisplayPanelLayout = new javax.swing.GroupLayout(startLatestOrdersDisplayPanel);
        startLatestOrdersDisplayPanel.setLayout(startLatestOrdersDisplayPanelLayout);
        startLatestOrdersDisplayPanelLayout.setHorizontalGroup(
            startLatestOrdersDisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 774, Short.MAX_VALUE)
        );
        startLatestOrdersDisplayPanelLayout.setVerticalGroup(
            startLatestOrdersDisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        jScrollPane6.setViewportView(startLatestOrdersDisplayPanel);

        fpDealsLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        fpDealsLabel1.setText("Start");

        javax.swing.GroupLayout startViewPanelLayout = new javax.swing.GroupLayout(startViewPanel);
        startViewPanel.setLayout(startViewPanelLayout);
        startViewPanelLayout.setHorizontalGroup(
            startViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(startViewPanelLayout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addGroup(startViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(separator9)
                    .addComponent(separator6)
                    .addComponent(recentlyBoughtLabel)
                    .addComponent(separator7)
                    .addComponent(dealsLabel)
                    .addComponent(popularLabel))
                .addContainerGap(50, Short.MAX_VALUE))
            .addGroup(startViewPanelLayout.createSequentialGroup()
                .addComponent(fpDealsLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        startViewPanelLayout.setVerticalGroup(
            startViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(startViewPanelLayout.createSequentialGroup()
                .addComponent(fpDealsLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(recentlyBoughtLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(popularLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                .addComponent(dealsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );

        featurePanel.add(startViewPanel, "cardStart");

        fpMyShoppingBagsLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        fpMyShoppingBagsLabel.setText("Mina matkassar");

        javax.swing.GroupLayout myShoppingBagsViewPanelLayout = new javax.swing.GroupLayout(myShoppingBagsViewPanel);
        myShoppingBagsViewPanel.setLayout(myShoppingBagsViewPanelLayout);
        myShoppingBagsViewPanelLayout.setHorizontalGroup(
            myShoppingBagsViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myShoppingBagsViewPanelLayout.createSequentialGroup()
                .addComponent(fpMyShoppingBagsLabel)
                .addGap(0, 681, Short.MAX_VALUE))
        );
        myShoppingBagsViewPanelLayout.setVerticalGroup(
            myShoppingBagsViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myShoppingBagsViewPanelLayout.createSequentialGroup()
                .addComponent(fpMyShoppingBagsLabel)
                .addGap(0, 576, Short.MAX_VALUE))
        );

        featurePanel.add(myShoppingBagsViewPanel, "cardBags");

        fpMyFavoritesLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        fpMyFavoritesLabel.setText("Mina favoriter");

        jScrollPane5.getVerticalScrollBar().setUnitIncrement(18);
        jScrollPane5.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout myFavoritesDisplayPanelLayout = new javax.swing.GroupLayout(myFavoritesDisplayPanel);
        myFavoritesDisplayPanel.setLayout(myFavoritesDisplayPanelLayout);
        myFavoritesDisplayPanelLayout.setHorizontalGroup(
            myFavoritesDisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 847, Short.MAX_VALUE)
        );
        myFavoritesDisplayPanelLayout.setVerticalGroup(
            myFavoritesDisplayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 552, Short.MAX_VALUE)
        );

        jScrollPane5.setViewportView(myFavoritesDisplayPanel);

        javax.swing.GroupLayout myFavoritesViewPanelLayout = new javax.swing.GroupLayout(myFavoritesViewPanel);
        myFavoritesViewPanel.setLayout(myFavoritesViewPanelLayout);
        myFavoritesViewPanelLayout.setHorizontalGroup(
            myFavoritesViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myFavoritesViewPanelLayout.createSequentialGroup()
                .addComponent(fpMyFavoritesLabel)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane5)
        );
        myFavoritesViewPanelLayout.setVerticalGroup(
            myFavoritesViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myFavoritesViewPanelLayout.createSequentialGroup()
                .addComponent(fpMyFavoritesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane5)
                .addContainerGap())
        );

        featurePanel.add(myFavoritesViewPanel, "cardFavorites");

        fpRecipesLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        fpRecipesLabel.setText("Recept");

        javax.swing.GroupLayout recipesViewPanelLayout = new javax.swing.GroupLayout(recipesViewPanel);
        recipesViewPanel.setLayout(recipesViewPanelLayout);
        recipesViewPanelLayout.setHorizontalGroup(
            recipesViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recipesViewPanelLayout.createSequentialGroup()
                .addComponent(fpRecipesLabel)
                .addGap(0, 776, Short.MAX_VALUE))
        );
        recipesViewPanelLayout.setVerticalGroup(
            recipesViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recipesViewPanelLayout.createSequentialGroup()
                .addComponent(fpRecipesLabel)
                .addGap(0, 576, Short.MAX_VALUE))
        );

        featurePanel.add(recipesViewPanel, "cardRecipes");

        fpDealsLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        fpDealsLabel.setText("Erbjudanden");

        javax.swing.GroupLayout dealsViewPanelLayout = new javax.swing.GroupLayout(dealsViewPanel);
        dealsViewPanel.setLayout(dealsViewPanelLayout);
        dealsViewPanelLayout.setHorizontalGroup(
            dealsViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dealsViewPanelLayout.createSequentialGroup()
                .addComponent(fpDealsLabel)
                .addGap(0, 716, Short.MAX_VALUE))
        );
        dealsViewPanelLayout.setVerticalGroup(
            dealsViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dealsViewPanelLayout.createSequentialGroup()
                .addComponent(fpDealsLabel)
                .addGap(0, 576, Short.MAX_VALUE))
        );

        featurePanel.add(dealsViewPanel, "cardDeals");

        fpHistoryLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        fpHistoryLabel.setText("Historik");

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel2.setBackground(new java.awt.Color(255, 248, 248));

        historyItemShower.setLayout(new java.awt.GridLayout(1, 2));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(historyItemShower, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(historyItemShower, javax.swing.GroupLayout.DEFAULT_SIZE, 534, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane3.setViewportView(jPanel2);

        orderHistoryList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "test", "listSak2" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        orderHistoryList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        orderHistoryList.setToolTipText("Välj order efter datum");
        orderHistoryList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                orderHistoryListValueChanged(evt);
            }
        });
        jScrollPane4.setViewportView(orderHistoryList);

        addHistoryToCart.setBackground(new java.awt.Color(215, 173, 173));
        addHistoryToCart.setText("Lägg till i varukorg");
        addHistoryToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addHistoryToCartActionPerformed(evt);
            }
        });

        historyNbrOfItemsLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        historyNbrOfItemsLabel.setText("Antal varor");

        historyNbrOfItemsPHLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        historyNbrOfItemsPHLabel.setText("0");

        historyTotalCostlabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        historyTotalCostlabel.setText("Total kostnad");

        historyTotalCostPHLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        historyTotalCostPHLabel.setText("0 kr");

        jButton1.setBackground(new java.awt.Color(255, 0, 0));
        jButton1.setText("Radera order");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout historyViewPanelLayout = new javax.swing.GroupLayout(historyViewPanel);
        historyViewPanel.setLayout(historyViewPanelLayout);
        historyViewPanelLayout.setHorizontalGroup(
            historyViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(historyViewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(historyViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fpHistoryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addComponent(jButton1)
                .addGap(113, 113, 113)
                .addGroup(historyViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(historyNbrOfItemsLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(historyTotalCostlabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(historyViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(historyNbrOfItemsPHLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(historyTotalCostPHLabel))
                .addGap(131, 131, 131)
                .addComponent(addHistoryToCart)
                .addGap(47, 47, 47))
            .addGroup(historyViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, historyViewPanelLayout.createSequentialGroup()
                    .addContainerGap(225, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(45, Short.MAX_VALUE)))
        );
        historyViewPanelLayout.setVerticalGroup(
            historyViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(historyViewPanelLayout.createSequentialGroup()
                .addGroup(historyViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(historyViewPanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(addHistoryToCart, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(historyViewPanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(historyViewPanelLayout.createSequentialGroup()
                .addGroup(historyViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fpHistoryLabel)
                    .addComponent(historyNbrOfItemsLabel)
                    .addComponent(historyNbrOfItemsPHLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(historyViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(historyTotalCostlabel)
                    .addComponent(historyTotalCostPHLabel))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(historyViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, historyViewPanelLayout.createSequentialGroup()
                    .addContainerGap(58, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        featurePanel.add(historyViewPanel, "cardHistory");

        categoryFeatureLabel.setText("Kategorinamn");

        javax.swing.GroupLayout categoryFeaturePanelLayout = new javax.swing.GroupLayout(categoryFeaturePanel);
        categoryFeaturePanel.setLayout(categoryFeaturePanelLayout);
        categoryFeaturePanelLayout.setHorizontalGroup(
            categoryFeaturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(categoryFeaturePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(categoryFeatureLabel)
                .addContainerGap(773, Short.MAX_VALUE))
        );
        categoryFeaturePanelLayout.setVerticalGroup(
            categoryFeaturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(categoryFeaturePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(categoryFeatureLabel)
                .addContainerGap(583, Short.MAX_VALUE))
        );

        featurePanel.add(categoryFeaturePanel, "categoryFeaturePanel");

        searchResultPanel.setBackground(new java.awt.Color(255, 248, 248));
        searchResultPanel.setLayout(new java.awt.BorderLayout());

        jScrollPane1.getVerticalScrollBar().setUnitIncrement(18);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(255, 248, 248));

        itemShower.setBackground(new java.awt.Color(255, 248, 248));
        itemShower.setLayout(new java.awt.GridLayout(1, 2));
        jPanel1.add(itemShower);

        jScrollPane1.setViewportView(jPanel1);

        searchResultPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        featurePanel.add(searchResultPanel, "searchResultPanel");

        myAccountPanel.setBackground(new java.awt.Color(255, 248, 248));
        myAccountPanel.setToolTipText("");
        myAccountPanel.setPreferredSize(new java.awt.Dimension(1121, 525));

        myAccountContentPanel.setBackground(new java.awt.Color(255, 248, 248));

        personalInfoPanel.setBackground(new java.awt.Color(255, 248, 248));

        personalInfoLabel.setFont(new java.awt.Font("Gautami", 0, 18)); // NOI18N
        personalInfoLabel.setForeground(new java.awt.Color(51, 51, 51));
        personalInfoLabel.setText("Personlig information");

        firstNameLabel.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        firstNameLabel.setForeground(new java.awt.Color(51, 51, 51));
        firstNameLabel.setText("Förnamn");

        firstNameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                firstNameTextFieldFirstNameUpdate(evt);
            }
        });

        lastNameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lastNameTextFieldLastNameUpdate(evt);
            }
        });

        lastNameLabel.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        lastNameLabel.setForeground(new java.awt.Color(51, 51, 51));
        lastNameLabel.setText("Efternamn");

        address1TextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                address1TextFieldAddress1Update(evt);
            }
        });

        adress1Label.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        adress1Label.setForeground(new java.awt.Color(51, 51, 51));
        adress1Label.setText("Adress 1");

        address2TextField.setEditable(false);
        address2TextField.setBackground(new java.awt.Color(204, 204, 204));

        adress2Label.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        adress2Label.setForeground(new java.awt.Color(153, 153, 153));
        adress2Label.setText("Adress 2");

        postCodeLabel.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        postCodeLabel.setForeground(new java.awt.Color(51, 51, 51));
        postCodeLabel.setText("Postnummer");

        postCodeTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                postCodeTextFieldPostCodeUpdate(evt);
            }
        });

        cityLabel.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        cityLabel.setForeground(new java.awt.Color(153, 153, 153));
        cityLabel.setText("Ort");

        cityTextField.setEditable(false);
        cityTextField.setBackground(new java.awt.Color(204, 204, 204));

        emailLabel.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        emailLabel.setForeground(new java.awt.Color(51, 51, 51));
        emailLabel.setText("E-mailadress");

        emailTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                emailTextFieldEmailUpdate(evt);
            }
        });

        separator1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redSeparator.png"))); // NOI18N
        separator1.setPreferredSize(new java.awt.Dimension(285, 2));

        phoneNumberTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                phoneNumberTextFieldphoneNumberUpdate(evt);
            }
        });

        phoneNumberLabel.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        phoneNumberLabel.setForeground(new java.awt.Color(51, 51, 51));
        phoneNumberLabel.setText("Telefon");

        javax.swing.GroupLayout personalInfoPanelLayout = new javax.swing.GroupLayout(personalInfoPanel);
        personalInfoPanel.setLayout(personalInfoPanelLayout);
        personalInfoPanelLayout.setHorizontalGroup(
            personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personalInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(personalInfoPanelLayout.createSequentialGroup()
                            .addComponent(adress1Label)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(address1TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(personalInfoPanelLayout.createSequentialGroup()
                            .addComponent(lastNameLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(personalInfoPanelLayout.createSequentialGroup()
                            .addComponent(firstNameLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(personalInfoPanelLayout.createSequentialGroup()
                            .addComponent(personalInfoLabel)
                            .addGap(126, 126, 126))
                        .addGroup(personalInfoPanelLayout.createSequentialGroup()
                            .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(postCodeLabel)
                                .addComponent(adress2Label)
                                .addComponent(cityLabel)
                                .addComponent(emailLabel))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(address2TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(postCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(separator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, personalInfoPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(phoneNumberLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(phoneNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        personalInfoPanelLayout.setVerticalGroup(
            personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personalInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(personalInfoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(separator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameLabel)
                    .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lastNameLabel)
                    .addComponent(lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adress1Label)
                    .addComponent(address1TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adress2Label)
                    .addComponent(address2TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(postCodeLabel)
                    .addComponent(postCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cityLabel)
                    .addComponent(cityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLabel)
                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(phoneNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneNumberLabel))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        paymentInfoPanel.setBackground(new java.awt.Color(255, 248, 248));

        paymentInfoLabel.setFont(new java.awt.Font("Gautami", 0, 18)); // NOI18N
        paymentInfoLabel.setForeground(new java.awt.Color(51, 51, 51));
        paymentInfoLabel.setText("Betalning");

        cardNumberLabel.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        cardNumberLabel.setForeground(new java.awt.Color(51, 51, 51));
        cardNumberLabel.setText("Kortnummer");

        cardNumberTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cardNumberTextFieldCardNumberUpdate(evt);
            }
        });

        cardTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "VISA", "MasterCard", "PayPal" }));
        cardTypeComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cardTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardTypeComboBoxCardTypeUpdate(evt);
            }
        });

        cardTypeLabel.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        cardTypeLabel.setForeground(new java.awt.Color(51, 51, 51));
        cardTypeLabel.setText("Korttyp");

        expirationDateLabel.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        expirationDateLabel.setForeground(new java.awt.Color(51, 51, 51));
        expirationDateLabel.setText("Expiration date");

        validMonthComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        validMonthComboBox.setSelectedIndex(5);
        validMonthComboBox.setToolTipText("Månad");
        validMonthComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        validMonthComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validMonthComboBoxValidMonthUpdate(evt);
            }
        });

        expDateSlashLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        expDateSlashLabel.setForeground(new java.awt.Color(51, 51, 51));
        expDateSlashLabel.setText("/");

        validYearComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2015", "2016", "2017", "2018", "2019", "2020" }));
        validYearComboBox.setToolTipText("År");
        validYearComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        validYearComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validYearComboBoxValidYearUpdate(evt);
            }
        });

        verificationCodeLabel.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        verificationCodeLabel.setForeground(new java.awt.Color(51, 51, 51));
        verificationCodeLabel.setText("CVV");

        verificationCodeTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                verificationCodeTextFieldVerificationCodeUpdate(evt);
            }
        });

        cardHolderNameLabel.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        cardHolderNameLabel.setForeground(new java.awt.Color(51, 51, 51));
        cardHolderNameLabel.setText("Kortinnehavare");

        cardHolderTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cardHolderTextFieldCardHolderUpdate(evt);
            }
        });

        separator2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redSeparator.png"))); // NOI18N

        deliveryPanel.setBackground(new java.awt.Color(255, 248, 248));

        personalInfoLabel1.setFont(new java.awt.Font("Gautami", 0, 18)); // NOI18N
        personalInfoLabel1.setForeground(new java.awt.Color(51, 51, 51));
        personalInfoLabel1.setText("Leverans");

        deliveryDateLabel.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        deliveryDateLabel.setForeground(new java.awt.Color(153, 153, 153));
        deliveryDateLabel.setText("Leveranstid");

        deliveryTimeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10:00-12:00", "12:00-14:00", "14:00-16:00", "16:00-18:00", "18:00-20:00", "20:00-22:00" }));
        deliveryTimeComboBox.setSelectedIndex(1);
        deliveryTimeComboBox.setToolTipText("");
        deliveryTimeComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        deliveryTimeComboBox.setEnabled(false);

        deliveryDayComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "idag", "imorgon", "i övermorgon" }));
        deliveryDayComboBox.setSelectedIndex(1);
        deliveryDayComboBox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        deliveryDayComboBox.setEnabled(false);

        deliverToMyAdressPanel.setBackground(new java.awt.Color(255, 248, 248));

        deliverToMyAddressLabel.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        deliverToMyAddressLabel.setForeground(new java.awt.Color(153, 153, 153));
        deliverToMyAddressLabel.setText("Leverera till annan adress");

        deliveryCheckBox.setBackground(new java.awt.Color(255, 255, 255));
        deliveryCheckBox.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        deliveryCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deliveryCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout deliverToMyAdressPanelLayout = new javax.swing.GroupLayout(deliverToMyAdressPanel);
        deliverToMyAdressPanel.setLayout(deliverToMyAdressPanelLayout);
        deliverToMyAdressPanelLayout.setHorizontalGroup(
            deliverToMyAdressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deliverToMyAdressPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deliverToMyAddressLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deliveryCheckBox)
                .addGap(120, 120, 120))
        );
        deliverToMyAdressPanelLayout.setVerticalGroup(
            deliverToMyAdressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, deliverToMyAdressPanelLayout.createSequentialGroup()
                .addGroup(deliverToMyAdressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(deliveryCheckBox, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deliverToMyAddressLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        separator3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redSeparator.png"))); // NOI18N

        addressPanel.setBackground(new java.awt.Color(255, 248, 248));

        adress1Label1.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        adress1Label1.setForeground(new java.awt.Color(51, 51, 51));
        adress1Label1.setText("Adress 1");

        address1TextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                address1TextField1AddressUpdate(evt);
            }
        });

        adress2TextField1.setEditable(false);
        adress2TextField1.setBackground(new java.awt.Color(204, 204, 204));

        adress2Label1.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        adress2Label1.setForeground(new java.awt.Color(153, 153, 153));
        adress2Label1.setText("Adress 2");

        codeLabel1.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        codeLabel1.setForeground(new java.awt.Color(51, 51, 51));
        codeLabel1.setText("Postnummer");

        postCodeTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                postCodeTextField1PostCodeUpdate(evt);
            }
        });

        cityTextField1.setEditable(false);
        cityTextField1.setBackground(new java.awt.Color(204, 204, 204));

        cityLabel1.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        cityLabel1.setForeground(new java.awt.Color(153, 153, 153));
        cityLabel1.setText("Ort");

        javax.swing.GroupLayout addressPanelLayout = new javax.swing.GroupLayout(addressPanel);
        addressPanel.setLayout(addressPanelLayout);
        addressPanelLayout.setHorizontalGroup(
            addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addressPanelLayout.createSequentialGroup()
                .addComponent(adress1Label1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(address1TextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addressPanelLayout.createSequentialGroup()
                .addComponent(adress2Label1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(adress2TextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addressPanelLayout.createSequentialGroup()
                .addComponent(codeLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(postCodeTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addressPanelLayout.createSequentialGroup()
                .addComponent(cityLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cityTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        addressPanelLayout.setVerticalGroup(
            addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addressPanelLayout.createSequentialGroup()
                .addGroup(addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adress1Label1)
                    .addComponent(address1TextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adress2Label1)
                    .addComponent(adress2TextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codeLabel1)
                    .addComponent(postCodeTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cityLabel1)
                    .addComponent(cityTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        saveButton2.setBackground(new java.awt.Color(255, 255, 255));
        saveButton2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        saveButton2.setForeground(new java.awt.Color(0, 51, 0));
        saveButton2.setText("Spara ändringar");
        saveButton2.setToolTipText("Spara ändringar");
        saveButton2.setBorder(null);
        saveButton2.setBorderPainted(false);
        saveButton2.setContentAreaFilled(false);
        saveButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        saveButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveButtonSaveButtonClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                saveButtonSaveButtonHover(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                saveButtonSaveButtonUnhover(evt);
            }
        });
        saveButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout deliveryPanelLayout = new javax.swing.GroupLayout(deliveryPanel);
        deliveryPanel.setLayout(deliveryPanelLayout);
        deliveryPanelLayout.setHorizontalGroup(
            deliveryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deliveryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(deliveryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(deliveryPanelLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(deliveryDateLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deliveryTimeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deliveryDayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(deliveryPanelLayout.createSequentialGroup()
                        .addGroup(deliveryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(separator3, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deliverToMyAdressPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(personalInfoLabel1))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(addressPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        deliveryPanelLayout.setVerticalGroup(
            deliveryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deliveryPanelLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(personalInfoLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(deliveryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deliveryDateLabel)
                    .addComponent(deliveryTimeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deliveryDayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deliverToMyAdressPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addressPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout paymentInfoPanelLayout = new javax.swing.GroupLayout(paymentInfoPanel);
        paymentInfoPanel.setLayout(paymentInfoPanelLayout);
        paymentInfoPanelLayout.setHorizontalGroup(
            paymentInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentInfoPanelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(paymentInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paymentInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(paymentInfoPanelLayout.createSequentialGroup()
                            .addGroup(paymentInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cardNumberLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cardTypeLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(expirationDateLabel, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(verificationCodeLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(paymentInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cardTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cardNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(paymentInfoPanelLayout.createSequentialGroup()
                                    .addComponent(validMonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(expDateSlashLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(validYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(verificationCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(paymentInfoPanelLayout.createSequentialGroup()
                            .addComponent(cardHolderNameLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cardHolderTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(separator2)
                    .addComponent(paymentInfoLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(paymentInfoPanelLayout.createSequentialGroup()
                .addComponent(deliveryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        paymentInfoPanelLayout.setVerticalGroup(
            paymentInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paymentInfoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(separator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paymentInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cardNumberLabel)
                    .addComponent(cardNumberTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paymentInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cardTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardTypeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paymentInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(expirationDateLabel)
                    .addComponent(validMonthComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(validYearComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(expDateSlashLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paymentInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(verificationCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(verificationCodeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paymentInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cardHolderNameLabel)
                    .addComponent(cardHolderTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deliveryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        saveButton.setBackground(new java.awt.Color(255, 255, 255));
        saveButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        saveButton.setForeground(new java.awt.Color(0, 51, 0));
        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/greenButton.png"))); // NOI18N
        saveButton.setText("Spara ändringar");
        saveButton.setToolTipText("Spara ändringar");
        saveButton.setBorder(null);
        saveButton.setBorderPainted(false);
        saveButton.setContentAreaFilled(false);
        saveButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        saveButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout myAccountContentPanelLayout = new javax.swing.GroupLayout(myAccountContentPanel);
        myAccountContentPanel.setLayout(myAccountContentPanelLayout);
        myAccountContentPanelLayout.setHorizontalGroup(
            myAccountContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, myAccountContentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(myAccountContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(myAccountContentPanelLayout.createSequentialGroup()
                        .addComponent(personalInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, myAccountContentPanelLayout.createSequentialGroup()
                        .addComponent(saveButton)
                        .addGap(61, 61, 61)))
                .addComponent(paymentInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
        );
        myAccountContentPanelLayout.setVerticalGroup(
            myAccountContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myAccountContentPanelLayout.createSequentialGroup()
                .addComponent(paymentInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(myAccountContentPanelLayout.createSequentialGroup()
                .addComponent(personalInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        myAccountTopPanel.setBackground(new java.awt.Color(255, 248, 248));
        myAccountTopPanel.setForeground(new java.awt.Color(255, 0, 51));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("Mitt konto -");

        titleNameLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        titleNameLabel.setForeground(new java.awt.Color(51, 51, 51));
        titleNameLabel.setText("Namn");

        javax.swing.GroupLayout myAccountTopPanelLayout = new javax.swing.GroupLayout(myAccountTopPanel);
        myAccountTopPanel.setLayout(myAccountTopPanelLayout);
        myAccountTopPanelLayout.setHorizontalGroup(
            myAccountTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myAccountTopPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(titleNameLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        myAccountTopPanelLayout.setVerticalGroup(
            myAccountTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myAccountTopPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(myAccountTopPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(titleNameLabel))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout myAccountPanelLayout = new javax.swing.GroupLayout(myAccountPanel);
        myAccountPanel.setLayout(myAccountPanelLayout);
        myAccountPanelLayout.setHorizontalGroup(
            myAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(myAccountContentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(myAccountTopPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        myAccountPanelLayout.setVerticalGroup(
            myAccountPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, myAccountPanelLayout.createSequentialGroup()
                .addComponent(myAccountTopPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(myAccountContentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        featurePanel.add(myAccountPanel, "myAccount");

        checkoutPanel.setBackground(new java.awt.Color(255, 255, 255));
        checkoutPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        progressPanel.setBackground(new java.awt.Color(255, 255, 255));

        checkoutShoppingCartLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        checkoutShoppingCartLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/purchaseStep.png"))); // NOI18N
        checkoutShoppingCartLabel.setText("Kundvagn");
        checkoutShoppingCartLabel.setFocusCycleRoot(true);
        checkoutShoppingCartLabel.setIconTextGap(-90);

        checkoutDeliveryLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        checkoutDeliveryLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/purchaseStep.png"))); // NOI18N
        checkoutDeliveryLabel.setText("Leverans");
        checkoutDeliveryLabel.setFocusCycleRoot(true);
        checkoutDeliveryLabel.setFocusTraversalPolicyProvider(true);
        checkoutDeliveryLabel.setIconTextGap(-85);

        checkoutPaymentLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        checkoutPaymentLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/purchaseStep.png"))); // NOI18N
        checkoutPaymentLabel.setText("Betalning");
        checkoutPaymentLabel.setFocusCycleRoot(true);
        checkoutPaymentLabel.setIconTextGap(-85);

        checkoutAcceptLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        checkoutAcceptLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/purchaseStep.png"))); // NOI18N
        checkoutAcceptLabel.setText("Slutför köp");
        checkoutAcceptLabel.setFocusCycleRoot(true);
        checkoutAcceptLabel.setIconTextGap(-90);

        arrow1Label.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        arrow1Label.setForeground(new java.awt.Color(153, 153, 153));
        arrow1Label.setText(">");

        arrow2Label.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        arrow2Label.setForeground(new java.awt.Color(153, 153, 153));
        arrow2Label.setText(">");

        arrow3Label.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        arrow3Label.setForeground(new java.awt.Color(153, 153, 153));
        arrow3Label.setText(">");

        javax.swing.GroupLayout progressPanelLayout = new javax.swing.GroupLayout(progressPanel);
        progressPanel.setLayout(progressPanelLayout);
        progressPanelLayout.setHorizontalGroup(
            progressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(progressPanelLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(checkoutShoppingCartLabel)
                .addGap(18, 18, 18)
                .addComponent(arrow1Label)
                .addGap(18, 18, 18)
                .addComponent(checkoutDeliveryLabel)
                .addGap(18, 18, 18)
                .addComponent(arrow2Label)
                .addGap(18, 18, 18)
                .addComponent(checkoutPaymentLabel)
                .addGap(18, 18, 18)
                .addComponent(arrow3Label)
                .addGap(18, 18, 18)
                .addComponent(checkoutAcceptLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        progressPanelLayout.setVerticalGroup(
            progressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, progressPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(progressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkoutShoppingCartLabel)
                    .addComponent(checkoutDeliveryLabel)
                    .addComponent(checkoutPaymentLabel)
                    .addComponent(checkoutAcceptLabel)
                    .addComponent(arrow1Label)
                    .addComponent(arrow2Label)
                    .addComponent(arrow3Label))
                .addContainerGap())
        );

        checkoutPanel.add(progressPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 19, -1, -1));

        productPanel.setBackground(new java.awt.Color(204, 204, 255));
        productPanel.setLayout(new java.awt.CardLayout());

        shoppingCartPanel.setBackground(new java.awt.Color(255, 255, 255));

        productScrollPane.getVerticalScrollBar().setUnitIncrement(18);
        productScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        productScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        productScrollPane.setHorizontalScrollBar(null);

        javax.swing.GroupLayout prodPanelLayout = new javax.swing.GroupLayout(prodPanel);
        prodPanel.setLayout(prodPanelLayout);
        prodPanelLayout.setHorizontalGroup(
            prodPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 579, Short.MAX_VALUE)
        );
        prodPanelLayout.setVerticalGroup(
            prodPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 466, Short.MAX_VALUE)
        );

        productScrollPane.setViewportView(prodPanel);

        nextStepButtonToPersonalInfo.setText("Gå vidare");
        nextStepButtonToPersonalInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextStepToPersonalInfo(evt);
            }
        });

        totalPriceLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        totalPriceLabel.setForeground(new java.awt.Color(51, 51, 51));
        totalPriceLabel.setText("Totalt");

        priceLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        priceLabel.setForeground(new java.awt.Color(51, 51, 51));
        priceLabel.setText("pris");

        krLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        krLabel.setForeground(new java.awt.Color(51, 51, 51));
        krLabel.setText("kr");

        recommendedProductsPanel.setBackground(new java.awt.Color(255, 255, 255));
        recommendedProductsPanel.setPreferredSize(new java.awt.Dimension(192, 400));

        recommendedProductsLabel.setFont(new java.awt.Font("Gautami", 0, 18)); // NOI18N
        recommendedProductsLabel.setForeground(new java.awt.Color(102, 102, 102));
        recommendedProductsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        recommendedProductsLabel.setText("Glömt?");
        recommendedProductsLabel.setToolTipText("Här visas favoriter som inte lagts i varukorgen");
        recommendedProductsLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        recommendedProductsLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        recommendedProductsScrollPane.getVerticalScrollBar().setUnitIncrement(18);
        recommendedProductsScrollPane.setBorder(null);
        recommendedProductsScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout recProdsInsideScrollLayout = new javax.swing.GroupLayout(recProdsInsideScroll);
        recProdsInsideScroll.setLayout(recProdsInsideScrollLayout);
        recProdsInsideScrollLayout.setHorizontalGroup(
            recProdsInsideScrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 172, Short.MAX_VALUE)
        );
        recProdsInsideScrollLayout.setVerticalGroup(
            recProdsInsideScrollLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 557, Short.MAX_VALUE)
        );

        recommendedProductsScrollPane.setViewportView(recProdsInsideScroll);

        javax.swing.GroupLayout recommendedProductsPanelLayout = new javax.swing.GroupLayout(recommendedProductsPanel);
        recommendedProductsPanel.setLayout(recommendedProductsPanelLayout);
        recommendedProductsPanelLayout.setHorizontalGroup(
            recommendedProductsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recommendedProductsPanelLayout.createSequentialGroup()
                .addGroup(recommendedProductsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(recommendedProductsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(recommendedProductsPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(recommendedProductsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        recommendedProductsPanelLayout.setVerticalGroup(
            recommendedProductsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recommendedProductsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(recommendedProductsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(recommendedProductsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout shoppingCartPanelLayout = new javax.swing.GroupLayout(shoppingCartPanel);
        shoppingCartPanel.setLayout(shoppingCartPanelLayout);
        shoppingCartPanelLayout.setHorizontalGroup(
            shoppingCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shoppingCartPanelLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(productScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(recommendedProductsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 179, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, shoppingCartPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(totalPriceLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(priceLabel)
                .addGap(12, 12, 12)
                .addComponent(krLabel)
                .addGap(51, 51, 51)
                .addComponent(nextStepButtonToPersonalInfo)
                .addGap(206, 206, 206))
        );
        shoppingCartPanelLayout.setVerticalGroup(
            shoppingCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shoppingCartPanelLayout.createSequentialGroup()
                .addGroup(shoppingCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(shoppingCartPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(productScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(recommendedProductsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 427, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(shoppingCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalPriceLabel)
                    .addComponent(priceLabel)
                    .addComponent(krLabel)
                    .addComponent(nextStepButtonToPersonalInfo))
                .addContainerGap(313, Short.MAX_VALUE))
        );

        productPanel.add(shoppingCartPanel, "shoppingCartPanel");

        checkPersonalInfoPanel.setBackground(new java.awt.Color(255, 255, 255));

        personalInfoPanel1.setBackground(new java.awt.Color(255, 255, 255));

        personalInfoLabel2.setFont(new java.awt.Font("Gautami", 0, 18)); // NOI18N
        personalInfoLabel2.setForeground(new java.awt.Color(51, 51, 51));
        personalInfoLabel2.setText("Personlig information");
        personalInfoLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        firstNameLabel1.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        firstNameLabel1.setForeground(new java.awt.Color(51, 51, 51));
        firstNameLabel1.setText("Förnamn");

        lastNameLabel1.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        lastNameLabel1.setForeground(new java.awt.Color(51, 51, 51));
        lastNameLabel1.setText("Efternamn");

        adress1Label2.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        adress1Label2.setForeground(new java.awt.Color(51, 51, 51));
        adress1Label2.setText("Adress 1");

        address2TextField1.setEditable(false);
        address2TextField1.setBackground(new java.awt.Color(204, 204, 204));

        adress2Label2.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        adress2Label2.setForeground(new java.awt.Color(153, 153, 153));
        adress2Label2.setText("Adress 2");

        postCodeLabel1.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        postCodeLabel1.setForeground(new java.awt.Color(51, 51, 51));
        postCodeLabel1.setText("Postnummer");

        cityLabel2.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        cityLabel2.setForeground(new java.awt.Color(153, 153, 153));
        cityLabel2.setText("Ort");

        cityTextField2.setEditable(false);
        cityTextField2.setBackground(new java.awt.Color(204, 204, 204));

        emailLabel1.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        emailLabel1.setForeground(new java.awt.Color(51, 51, 51));
        emailLabel1.setText("E-mailadress");

        separator4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redSeparator.png"))); // NOI18N
        separator4.setPreferredSize(new java.awt.Dimension(285, 2));

        phoneNumberLabel1.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        phoneNumberLabel1.setForeground(new java.awt.Color(51, 51, 51));
        phoneNumberLabel1.setText("Telefon");

        javax.swing.GroupLayout personalInfoPanel1Layout = new javax.swing.GroupLayout(personalInfoPanel1);
        personalInfoPanel1.setLayout(personalInfoPanel1Layout);
        personalInfoPanel1Layout.setHorizontalGroup(
            personalInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personalInfoPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(personalInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(personalInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(personalInfoPanel1Layout.createSequentialGroup()
                            .addComponent(adress1Label2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(checkoutAddressTF, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(personalInfoPanel1Layout.createSequentialGroup()
                            .addComponent(lastNameLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(checkoutLastNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(personalInfoPanel1Layout.createSequentialGroup()
                            .addComponent(firstNameLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(checkoutFirstNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(personalInfoPanel1Layout.createSequentialGroup()
                            .addComponent(personalInfoLabel2)
                            .addGap(126, 126, 126))
                        .addGroup(personalInfoPanel1Layout.createSequentialGroup()
                            .addGroup(personalInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(postCodeLabel1)
                                .addComponent(adress2Label2)
                                .addComponent(cityLabel2)
                                .addComponent(emailLabel1))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(personalInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(address2TextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(checkoutPostCodeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cityTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(checkoutEmailTF, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(separator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, personalInfoPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(phoneNumberLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkoutPhoneNumberTF, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        personalInfoPanel1Layout.setVerticalGroup(
            personalInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(personalInfoPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(personalInfoLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(separator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(personalInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(firstNameLabel1)
                    .addComponent(checkoutFirstNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lastNameLabel1)
                    .addComponent(checkoutLastNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adress1Label2)
                    .addComponent(checkoutAddressTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adress2Label2)
                    .addComponent(address2TextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(postCodeLabel1)
                    .addComponent(checkoutPostCodeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cityLabel2)
                    .addComponent(cityTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLabel1)
                    .addComponent(checkoutEmailTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkoutPhoneNumberTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneNumberLabel1))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        deliveryPanel1.setBackground(new java.awt.Color(255, 255, 255));

        personalInfoLabel3.setFont(new java.awt.Font("Gautami", 0, 18)); // NOI18N
        personalInfoLabel3.setForeground(new java.awt.Color(51, 51, 51));
        personalInfoLabel3.setText("Leverans");
        personalInfoLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        deliveryDateLabel1.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        deliveryDateLabel1.setForeground(new java.awt.Color(51, 51, 51));
        deliveryDateLabel1.setText("Leveranstid");

        deliveryTimeComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10:00-12:00", "12:00-14:00", "14:00-16:00", "16:00-18:00", "18:00-20:00", "20:00-22:00" }));
        deliveryTimeComboBox1.setSelectedIndex(1);
        deliveryTimeComboBox1.setToolTipText("");
        deliveryTimeComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        deliveryDayComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "idag", "imorgon", "om 2 dagar", "om 3 dagar" }));
        deliveryDayComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        deliverToMyAdressPanel1.setBackground(new java.awt.Color(255, 255, 255));

        deliverToMyAddressLabel1.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        deliverToMyAddressLabel1.setForeground(new java.awt.Color(153, 153, 153));
        deliverToMyAddressLabel1.setText("Leverera till annan adress");
        deliverToMyAddressLabel1.setEnabled(false);

        deliveryCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        deliveryCheckBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        deliveryCheckBox1.setEnabled(false);
        deliveryCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deliveryCheckBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout deliverToMyAdressPanel1Layout = new javax.swing.GroupLayout(deliverToMyAdressPanel1);
        deliverToMyAdressPanel1.setLayout(deliverToMyAdressPanel1Layout);
        deliverToMyAdressPanel1Layout.setHorizontalGroup(
            deliverToMyAdressPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deliverToMyAdressPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deliverToMyAddressLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deliveryCheckBox1)
                .addGap(120, 120, 120))
        );
        deliverToMyAdressPanel1Layout.setVerticalGroup(
            deliverToMyAdressPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, deliverToMyAdressPanel1Layout.createSequentialGroup()
                .addGroup(deliverToMyAdressPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(deliveryCheckBox1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(deliverToMyAddressLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        separator5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redSeparator.png"))); // NOI18N

        addressPanel2.setBackground(new java.awt.Color(255, 255, 255));

        adress1Label3.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        adress1Label3.setForeground(new java.awt.Color(51, 51, 51));
        adress1Label3.setText("Adress 1");
        adress1Label3.setEnabled(false);

        checkoutOtherAddressTF.setEnabled(false);

        adress2TextField2.setEditable(false);
        adress2TextField2.setBackground(new java.awt.Color(204, 204, 204));
        adress2TextField2.setEnabled(false);

        adress2Label3.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        adress2Label3.setForeground(new java.awt.Color(153, 153, 153));
        adress2Label3.setText("Adress 2");
        adress2Label3.setEnabled(false);

        codeLabel2.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        codeLabel2.setForeground(new java.awt.Color(51, 51, 51));
        codeLabel2.setText("Postnummer");
        codeLabel2.setEnabled(false);

        checkoutOtherPostCodeTF.setEnabled(false);

        cityTextField3.setEditable(false);
        cityTextField3.setBackground(new java.awt.Color(204, 204, 204));
        cityTextField3.setEnabled(false);

        cityLabel3.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        cityLabel3.setForeground(new java.awt.Color(153, 153, 153));
        cityLabel3.setText("Ort");
        cityLabel3.setEnabled(false);

        javax.swing.GroupLayout addressPanel2Layout = new javax.swing.GroupLayout(addressPanel2);
        addressPanel2.setLayout(addressPanel2Layout);
        addressPanel2Layout.setHorizontalGroup(
            addressPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addressPanel2Layout.createSequentialGroup()
                .addComponent(adress1Label3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkoutOtherAddressTF, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addressPanel2Layout.createSequentialGroup()
                .addComponent(adress2Label3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(adress2TextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addressPanel2Layout.createSequentialGroup()
                .addComponent(codeLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkoutOtherPostCodeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addressPanel2Layout.createSequentialGroup()
                .addComponent(cityLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cityTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        addressPanel2Layout.setVerticalGroup(
            addressPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addressPanel2Layout.createSequentialGroup()
                .addGroup(addressPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adress1Label3)
                    .addComponent(checkoutOtherAddressTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addressPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adress2Label3)
                    .addComponent(adress2TextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addressPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codeLabel2)
                    .addComponent(checkoutOtherPostCodeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addressPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cityLabel3)
                    .addComponent(cityTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        backButtonToShoppingCart.setText("Tillbaka");
        backButtonToShoppingCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToShoppingCart(evt);
            }
        });

        nextStepButtonToPayment.setText("Gå vidare");
        nextStepButtonToPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextStepToPayment(evt);
            }
        });

        saveButton1.setBackground(new java.awt.Color(255, 255, 255));
        saveButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        saveButton1.setForeground(new java.awt.Color(0, 51, 0));
        saveButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/greenButton.png"))); // NOI18N
        saveButton1.setText("Spara ändringar");
        saveButton1.setToolTipText("Spara ändringar");
        saveButton1.setBorder(null);
        saveButton1.setBorderPainted(false);
        saveButton1.setContentAreaFilled(false);
        saveButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        saveButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/greenButtonHover.png"))); // NOI18N
        saveButton1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/greenButtonClick.png"))); // NOI18N
        saveButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout deliveryPanel1Layout = new javax.swing.GroupLayout(deliveryPanel1);
        deliveryPanel1.setLayout(deliveryPanel1Layout);
        deliveryPanel1Layout.setHorizontalGroup(
            deliveryPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deliveryPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(deliveryPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(deliveryPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(deliveryDateLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deliveryTimeComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deliveryDayComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(deliveryPanel1Layout.createSequentialGroup()
                        .addGroup(deliveryPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(separator5, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(deliverToMyAdressPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(personalInfoLabel3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(addressPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, deliveryPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(deliveryPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(saveButton1)
                            .addGroup(deliveryPanel1Layout.createSequentialGroup()
                                .addComponent(backButtonToShoppingCart)
                                .addGap(18, 18, 18)
                                .addComponent(nextStepButtonToPayment)))
                        .addGap(44, 44, 44))))
        );
        deliveryPanel1Layout.setVerticalGroup(
            deliveryPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(deliveryPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(personalInfoLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(deliveryPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deliveryDateLabel1)
                    .addComponent(deliveryTimeComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deliveryDayComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deliverToMyAdressPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addressPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                .addComponent(saveButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(deliveryPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backButtonToShoppingCart)
                    .addComponent(nextStepButtonToPayment))
                .addContainerGap())
        );

        javax.swing.GroupLayout checkPersonalInfoPanelLayout = new javax.swing.GroupLayout(checkPersonalInfoPanel);
        checkPersonalInfoPanel.setLayout(checkPersonalInfoPanelLayout);
        checkPersonalInfoPanelLayout.setHorizontalGroup(
            checkPersonalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkPersonalInfoPanelLayout.createSequentialGroup()
                .addComponent(personalInfoPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deliveryPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(167, Short.MAX_VALUE))
        );
        checkPersonalInfoPanelLayout.setVerticalGroup(
            checkPersonalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkPersonalInfoPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(checkPersonalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(personalInfoPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deliveryPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(291, Short.MAX_VALUE))
        );

        productPanel.add(checkPersonalInfoPanel, "personalInfoDelivery");

        checkPaymentPanel.setBackground(new java.awt.Color(255, 255, 255));

        backButtonToPersonalInfo.setText("Tillbaka");
        backButtonToPersonalInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToPersonalInfo(evt);
            }
        });

        nextStepButtonToAccept.setText("Gå vidare");
        nextStepButtonToAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextStepToAccept(evt);
            }
        });

        paymentInfoPanel1.setBackground(new java.awt.Color(255, 255, 255));

        paymentInfoLabel1.setFont(new java.awt.Font("Gautami", 0, 18)); // NOI18N
        paymentInfoLabel1.setForeground(new java.awt.Color(51, 51, 51));
        paymentInfoLabel1.setText("Betalning");
        paymentInfoLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        cardNumberLabel1.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        cardNumberLabel1.setForeground(new java.awt.Color(51, 51, 51));
        cardNumberLabel1.setText("Kortnummer");

        checkoutCardTypeCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "VISA", "MasterCard", "PayPal" }));
        checkoutCardTypeCB.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        cardTypeLabel1.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        cardTypeLabel1.setForeground(new java.awt.Color(51, 51, 51));
        cardTypeLabel1.setText("Korttyp");

        expirationDateLabel1.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        expirationDateLabel1.setForeground(new java.awt.Color(51, 51, 51));
        expirationDateLabel1.setText("Expiration date");

        checkoutValidMonthCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        checkoutValidMonthCB.setSelectedIndex(5);
        checkoutValidMonthCB.setToolTipText("Månad");
        checkoutValidMonthCB.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        expDateSlashLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        expDateSlashLabel1.setForeground(new java.awt.Color(51, 51, 51));
        expDateSlashLabel1.setText("/");

        checkoutValidYearCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2015", "2016", "2017", "2018", "2019", "2020" }));
        checkoutValidYearCB.setToolTipText("År");
        checkoutValidYearCB.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        verificationCodeLabel1.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        verificationCodeLabel1.setForeground(new java.awt.Color(51, 51, 51));
        verificationCodeLabel1.setText("CVV");

        cardHolderNameLabel1.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        cardHolderNameLabel1.setForeground(new java.awt.Color(51, 51, 51));
        cardHolderNameLabel1.setText("Kortinnehavare");

        separator8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redSeparator.png"))); // NOI18N

        javax.swing.GroupLayout paymentInfoPanel1Layout = new javax.swing.GroupLayout(paymentInfoPanel1);
        paymentInfoPanel1.setLayout(paymentInfoPanel1Layout);
        paymentInfoPanel1Layout.setHorizontalGroup(
            paymentInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentInfoPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(paymentInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paymentInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(paymentInfoPanel1Layout.createSequentialGroup()
                            .addGroup(paymentInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cardNumberLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(cardTypeLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(expirationDateLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(verificationCodeLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(paymentInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(checkoutCardTypeCB, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(checkoutCardNumberTF, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(paymentInfoPanel1Layout.createSequentialGroup()
                                    .addComponent(checkoutValidMonthCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(expDateSlashLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(checkoutValidYearCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(checkoutVerificationCodeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(paymentInfoPanel1Layout.createSequentialGroup()
                            .addComponent(cardHolderNameLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(checkoutCardHolderTF, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(separator8)
                    .addComponent(paymentInfoLabel1))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        paymentInfoPanel1Layout.setVerticalGroup(
            paymentInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paymentInfoPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(paymentInfoLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(separator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(paymentInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cardNumberLabel1)
                    .addComponent(checkoutCardNumberTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paymentInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkoutCardTypeCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardTypeLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paymentInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(expirationDateLabel1)
                    .addComponent(checkoutValidMonthCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkoutValidYearCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(expDateSlashLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paymentInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkoutVerificationCodeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(verificationCodeLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paymentInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cardHolderNameLabel1)
                    .addComponent(checkoutCardHolderTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout checkPaymentPanelLayout = new javax.swing.GroupLayout(checkPaymentPanel);
        checkPaymentPanel.setLayout(checkPaymentPanelLayout);
        checkPaymentPanelLayout.setHorizontalGroup(
            checkPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, checkPaymentPanelLayout.createSequentialGroup()
                .addContainerGap(508, Short.MAX_VALUE)
                .addComponent(backButtonToPersonalInfo)
                .addGap(18, 18, 18)
                .addComponent(nextStepButtonToAccept)
                .addGap(185, 185, 185))
            .addGroup(checkPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(checkPaymentPanelLayout.createSequentialGroup()
                    .addGap(167, 167, 167)
                    .addComponent(paymentInfoPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(317, Short.MAX_VALUE)))
        );
        checkPaymentPanelLayout.setVerticalGroup(
            checkPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, checkPaymentPanelLayout.createSequentialGroup()
                .addContainerGap(453, Short.MAX_VALUE)
                .addGroup(checkPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nextStepButtonToAccept)
                    .addComponent(backButtonToPersonalInfo))
                .addGap(301, 301, 301))
            .addGroup(checkPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(checkPaymentPanelLayout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addComponent(paymentInfoPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(531, Short.MAX_VALUE)))
        );

        productPanel.add(checkPaymentPanel, "payment");

        acceptPanel.setBackground(new java.awt.Color(255, 255, 255));

        backButtonToPayment.setText("Tillbaka");
        backButtonToPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToPayment(evt);
            }
        });

        acceptPurchaseButton.setBackground(new java.awt.Color(51, 255, 51));
        acceptPurchaseButton.setText("Slutför köp");
        acceptPurchaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptPurchase(evt);
            }
        });

        acceptNameLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        acceptNameLabel.setForeground(new java.awt.Color(51, 51, 51));
        acceptNameLabel.setText("Namn Efternamn");

        acceptAddressLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        acceptAddressLabel.setForeground(new java.awt.Color(51, 51, 51));
        acceptAddressLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        acceptAddressLabel.setText("Adress");

        acceptPostCodeLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        acceptPostCodeLabel.setForeground(new java.awt.Color(51, 51, 51));
        acceptPostCodeLabel.setText("Postkod");

        acceptDeliveryLabel.setFont(new java.awt.Font("Gautami", 0, 18)); // NOI18N
        acceptDeliveryLabel.setForeground(new java.awt.Color(51, 51, 51));
        acceptDeliveryLabel.setText("Leverans");
        acceptDeliveryLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        acceptShoppingCartLabel.setFont(new java.awt.Font("Gautami", 0, 18)); // NOI18N
        acceptShoppingCartLabel.setForeground(new java.awt.Color(51, 51, 51));
        acceptShoppingCartLabel.setText("Kundvagn");
        acceptShoppingCartLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        acceptSeparator1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redSeparator.png"))); // NOI18N

        acceptSeparator3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redSeparator.png"))); // NOI18N

        acceptPaymentLabel.setFont(new java.awt.Font("Gautami", 0, 18)); // NOI18N
        acceptPaymentLabel.setForeground(new java.awt.Color(51, 51, 51));
        acceptPaymentLabel.setText("Betalning");
        acceptPaymentLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        acceptSeparator4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redSeparator.png"))); // NOI18N

        acceptCardNumberLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        acceptCardNumberLabel.setForeground(new java.awt.Color(51, 51, 51));
        acceptCardNumberLabel.setText("Kortnummer");

        acceptCardTypeLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        acceptCardTypeLabel.setForeground(new java.awt.Color(51, 51, 51));
        acceptCardTypeLabel.setText("Korttyp");

        acceptValidMonthYearLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        acceptValidMonthYearLabel.setForeground(new java.awt.Color(51, 51, 51));
        acceptValidMonthYearLabel.setText("Valid month & year");

        acceptCardHolderLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        acceptCardHolderLabel.setForeground(new java.awt.Color(51, 51, 51));
        acceptCardHolderLabel.setText("Kortinnehavare");

        acceptDeliveryTime.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        acceptDeliveryTime.setForeground(new java.awt.Color(51, 51, 51));
        acceptDeliveryTime.setText("Leveranstid");

        javax.swing.GroupLayout acceptPanelLayout = new javax.swing.GroupLayout(acceptPanel);
        acceptPanel.setLayout(acceptPanelLayout);
        acceptPanelLayout.setHorizontalGroup(
            acceptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, acceptPanelLayout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(acceptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(acceptShoppingCartLabel)
                    .addComponent(acceptSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 224, Short.MAX_VALUE)
                .addGroup(acceptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, acceptPanelLayout.createSequentialGroup()
                        .addGroup(acceptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acceptDeliveryLabel)
                            .addComponent(acceptSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acceptNameLabel)
                            .addComponent(acceptAddressLabel)
                            .addComponent(acceptPostCodeLabel)
                            .addComponent(acceptPaymentLabel)
                            .addComponent(acceptSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(acceptPanelLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addGroup(acceptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(acceptValidMonthYearLabel)
                                    .addGroup(acceptPanelLayout.createSequentialGroup()
                                        .addComponent(acceptCardTypeLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(acceptCardNumberLabel))
                                    .addComponent(acceptCardHolderLabel)))
                            .addComponent(acceptDeliveryTime))
                        .addGap(123, 123, 123))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, acceptPanelLayout.createSequentialGroup()
                        .addComponent(backButtonToPayment)
                        .addGap(31, 31, 31)
                        .addComponent(acceptPurchaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(147, 147, 147))))
        );
        acceptPanelLayout.setVerticalGroup(
            acceptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(acceptPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(acceptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(acceptPanelLayout.createSequentialGroup()
                        .addGroup(acceptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(acceptShoppingCartLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(acceptDeliveryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acceptSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(acceptSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(acceptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(acceptPanelLayout.createSequentialGroup()
                        .addComponent(acceptNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acceptAddressLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acceptPostCodeLabel)
                        .addGap(18, 18, 18)
                        .addComponent(acceptDeliveryTime)
                        .addGap(35, 35, 35)
                        .addComponent(acceptPaymentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acceptSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(acceptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(acceptCardNumberLabel)
                            .addComponent(acceptCardTypeLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acceptValidMonthYearLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acceptCardHolderLabel)
                        .addGap(92, 92, 92)
                        .addGroup(acceptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(acceptPurchaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(backButtonToPayment)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(298, Short.MAX_VALUE))
        );

        productPanel.add(acceptPanel, "accept");

        endOfCheckoutPanel.setBackground(new java.awt.Color(255, 255, 255));

        thanksLabel.setFont(new java.awt.Font("Gautami", 0, 24)); // NOI18N
        thanksLabel.setForeground(new java.awt.Color(51, 51, 51));
        thanksLabel.setText("Tack för att du handlar med iMat!");
        thanksLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        backToStoreButton.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        backToStoreButton.setText("Tillbaka till affären");
        backToStoreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToStore(evt);
            }
        });

        javax.swing.GroupLayout endOfCheckoutPanelLayout = new javax.swing.GroupLayout(endOfCheckoutPanel);
        endOfCheckoutPanel.setLayout(endOfCheckoutPanelLayout);
        endOfCheckoutPanelLayout.setHorizontalGroup(
            endOfCheckoutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(endOfCheckoutPanelLayout.createSequentialGroup()
                .addContainerGap(404, Short.MAX_VALUE)
                .addGroup(endOfCheckoutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, endOfCheckoutPanelLayout.createSequentialGroup()
                        .addComponent(thanksLabel)
                        .addGap(121, 121, 121))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, endOfCheckoutPanelLayout.createSequentialGroup()
                        .addComponent(backToStoreButton)
                        .addGap(217, 217, 217))))
        );
        endOfCheckoutPanelLayout.setVerticalGroup(
            endOfCheckoutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(endOfCheckoutPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(thanksLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addComponent(backToStoreButton)
                .addContainerGap(617, Short.MAX_VALUE))
        );

        productPanel.add(endOfCheckoutPanel, "end");

        checkoutPanel.add(productPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 77, -1, -1));

        featurePanel.add(checkoutPanel, "checkout");

        getContentPane().add(featurePanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(149, 83, 849, 605));

        cartPanel.setBackground(new java.awt.Color(215, 173, 173));

        goToCashierBtn.setBackground(new java.awt.Color(215, 173, 173));
        goToCashierBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        goToCashierBtn.setText("Gå till kassa");
        goToCashierBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToCashierBtnActionPerformed(evt);
            }
        });

        cartPanelScrollPane.getVerticalScrollBar().setUnitIncrement(18);

        cartContentsPanel.setBackground(new java.awt.Color(215, 173, 173));
        cartPanelScrollPane.setViewportView(cartContentsPanel);

        cartPanelNbrOfItemsLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cartPanelNbrOfItemsLabel.setForeground(new java.awt.Color(51, 51, 51));
        cartPanelNbrOfItemsLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cartPanelNbrOfItemsLabel.setText("0 st");

        tpCartContentsNumber.setForeground(new java.awt.Color(51, 51, 51));
        tpCartContentsNumber.setText("Varor");

        cartPanelTotalCostLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        cartPanelTotalCostLabel.setForeground(new java.awt.Color(51, 51, 51));
        cartPanelTotalCostLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        cartPanelTotalCostLabel.setText("0 kr");

        tpCartSumLabel.setForeground(new java.awt.Color(51, 51, 51));
        tpCartSumLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tpCartSumLabel.setText("Totalt");

        cartImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/shoppingCart.png"))); // NOI18N
        cartImage.setToolTipText("Kundvagn");
        cartImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cartImageMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout cartPanelLayout = new javax.swing.GroupLayout(cartPanel);
        cartPanel.setLayout(cartPanelLayout);
        cartPanelLayout.setHorizontalGroup(
            cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cartPanelLayout.createSequentialGroup()
                .addGroup(cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(cartPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(goToCashierBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, cartPanelLayout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(cartImage)
                        .addGap(18, 18, 18)
                        .addGroup(cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(cartPanelLayout.createSequentialGroup()
                                .addComponent(tpCartContentsNumber)
                                .addGap(18, 18, 18)
                                .addComponent(cartPanelNbrOfItemsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(cartPanelLayout.createSequentialGroup()
                                .addComponent(tpCartSumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cartPanelTotalCostLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 61, Short.MAX_VALUE))
                    .addComponent(cartPanelScrollPane))
                .addContainerGap())
        );
        cartPanelLayout.setVerticalGroup(
            cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cartPanelLayout.createSequentialGroup()
                        .addGroup(cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cartPanelNbrOfItemsLabel)
                            .addComponent(tpCartContentsNumber))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cartPanelTotalCostLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tpCartSumLabel)))
                    .addComponent(cartImage, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cartPanelScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(goToCashierBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        getContentPane().add(cartPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 90, 270, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoPanelMouseClicked
        refreshStartPage();
        cl.show(featurePanel, "cardStart");
    }//GEN-LAST:event_logoPanelMouseClicked

    private void myShoppingBagsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myShoppingBagsBtnActionPerformed
        cl.show(featurePanel, "cardBags");
    }//GEN-LAST:event_myShoppingBagsBtnActionPerformed

    private void recipesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recipesBtnActionPerformed
        cl.show(featurePanel, "cardRecipes");
    }//GEN-LAST:event_recipesBtnActionPerformed

    private void historyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyBtnActionPerformed
                cl.show(featurePanel, "cardHistory");    
        displayHistory();
        revalidate();
        repaint();
    }//GEN-LAST:event_historyBtnActionPerformed

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchFieldActionPerformed

    private void deliveryCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deliveryCheckBoxActionPerformed
        // Happens when the user checks/unchecks the delivery checkbox.

        if(deliveryCheckBox.isSelected()){
            addressPanel.setVisible(true);
        } else{
            addressPanel.setVisible(false);
        }

    }//GEN-LAST:event_deliveryCheckBoxActionPerformed

    private void saveButtonSaveButtonClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonSaveButtonClicked
        
    }//GEN-LAST:event_saveButtonSaveButtonClicked

    private void saveButtonSaveButtonHover(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonSaveButtonHover
        
    }//GEN-LAST:event_saveButtonSaveButtonHover

    private void saveButtonSaveButtonUnhover(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonSaveButtonUnhover
        
    }//GEN-LAST:event_saveButtonSaveButtonUnhover

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        gpCon.iMDH.shutDown();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void tpMyAccountLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tpMyAccountLabelMouseClicked
        setValuesMyAccount();
        cl.show(featurePanel, "myAccount");
    }//GEN-LAST:event_tpMyAccountLabelMouseClicked

    private void deliveryCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deliveryCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deliveryCheckBox1ActionPerformed

    private void nextStepToPersonalInfo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextStepToPersonalInfo
        recommendedProductsPanel.setVisible(false);
        setDeliveryInfoCheckout();
        changeActivePurchaseStep("personalInfoDelivery", "shoppingCart");
        cl2.show(productPanel, "personalInfoDelivery");
    }//GEN-LAST:event_nextStepToPersonalInfo

    private void nextStepToPayment(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextStepToPayment
        setPaymentInfoCheckout();
        setDeliveryAdress();
        
        changeActivePurchaseStep("payment", "personalInfoDelivery");
        cl2.show(productPanel, "payment");
    }//GEN-LAST:event_nextStepToPayment

    private void goToCashierBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goToCashierBtnActionPerformed

        resetActivePurchaseStep();
//        System.out.print("Kassa cpo-size: " + gpCon.cpo.size());
        
        cl2.show(productPanel, "shoppingCartPanel");
        cl.show(featurePanel, "checkout");

        progressPanel.setVisible(true);
        recommendedProductsPanel.setVisible(true);
        
        addRecommendations();
        
    }//GEN-LAST:event_goToCashierBtnActionPerformed

    private void nextStepToAccept(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextStepToAccept
        // Go to the next step, to accept.
        changeActivePurchaseStep("accept", "payment");
        setDeliveryAdress();
        cl2.show(productPanel, "accept");
    }//GEN-LAST:event_nextStepToAccept

    private void postCodeTextField1PostCodeUpdate(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_postCodeTextField1PostCodeUpdate
        gpCon.iMDH.getCustomer().setPostCode(postCodeTextField.getText()); //getInstance efter iMDH
    }//GEN-LAST:event_postCodeTextField1PostCodeUpdate

    private void address1TextField1AddressUpdate(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_address1TextField1AddressUpdate
        gpCon.iMDH.getCustomer().setPostAddress(address1TextField.getText());
    }//GEN-LAST:event_address1TextField1AddressUpdate

    private void cardHolderTextFieldCardHolderUpdate(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cardHolderTextFieldCardHolderUpdate
        gpCon.iMDH.getCreditCard().setHoldersName(cardHolderTextField.getText());
    }//GEN-LAST:event_cardHolderTextFieldCardHolderUpdate

    private void verificationCodeTextFieldVerificationCodeUpdate(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_verificationCodeTextFieldVerificationCodeUpdate
        try{
            gpCon.iMDH.getCreditCard().setVerificationCode(Integer.parseInt(verificationCodeTextField.getText()));
        } catch(NumberFormatException nfe){
            System.out.println("Incorrect input.");
        }
    }//GEN-LAST:event_verificationCodeTextFieldVerificationCodeUpdate

    private void validYearComboBoxValidYearUpdate(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validYearComboBoxValidYearUpdate
        gpCon.iMDH.getCreditCard().setValidYear(validYearComboBox.getSelectedIndex()+ 2015);
    }//GEN-LAST:event_validYearComboBoxValidYearUpdate

    private void validMonthComboBoxValidMonthUpdate(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validMonthComboBoxValidMonthUpdate
        gpCon.iMDH.getCreditCard().setValidMonth(validMonthComboBox.getSelectedIndex());
    }//GEN-LAST:event_validMonthComboBoxValidMonthUpdate

    private void cardTypeComboBoxCardTypeUpdate(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardTypeComboBoxCardTypeUpdate
        gpCon.iMDH.getCreditCard().setCardType(cardTypeComboBox.getSelectedItem().toString());
    }//GEN-LAST:event_cardTypeComboBoxCardTypeUpdate

    private void cardNumberTextFieldCardNumberUpdate(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cardNumberTextFieldCardNumberUpdate
        gpCon.iMDH.getCreditCard().setCardNumber(cardNumberTextField.getText());
    }//GEN-LAST:event_cardNumberTextFieldCardNumberUpdate

    private void phoneNumberTextFieldphoneNumberUpdate(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneNumberTextFieldphoneNumberUpdate
        gpCon.iMDH.getCustomer().setPhoneNumber(phoneNumberTextField.getText());
    }//GEN-LAST:event_phoneNumberTextFieldphoneNumberUpdate

    private void postCodeTextFieldPostCodeUpdate(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_postCodeTextFieldPostCodeUpdate
        gpCon.iMDH.getCustomer().setPostCode(postCodeTextField.getText());
    }//GEN-LAST:event_postCodeTextFieldPostCodeUpdate

    private void emailTextFieldEmailUpdate(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailTextFieldEmailUpdate
        gpCon.iMDH.getCustomer().setEmail(emailTextField.getText());
    }//GEN-LAST:event_emailTextFieldEmailUpdate

    private void address1TextFieldAddress1Update(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_address1TextFieldAddress1Update
        gpCon.iMDH.getCustomer().setAddress(address1TextField.getText());
    }//GEN-LAST:event_address1TextFieldAddress1Update

    private void lastNameTextFieldLastNameUpdate(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lastNameTextFieldLastNameUpdate
        gpCon.iMDH.getCustomer().setLastName(lastNameTextField.getText());
    }//GEN-LAST:event_lastNameTextFieldLastNameUpdate

    private void firstNameTextFieldFirstNameUpdate(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_firstNameTextFieldFirstNameUpdate
        gpCon.iMDH.getCustomer().setFirstName(firstNameTextField.getText());
    }//GEN-LAST:event_firstNameTextFieldFirstNameUpdate

    private void acceptPurchase(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptPurchase
        gpCon.iMDH.placeOrder();
        gpCon.iMDH.shutDown();
        updateOrderListModel();
//        listModel.clear();
        
//        System.out.println("antal ordrar: " + gpCon.iMDH.getOrders().size());
        cl2.show(productPanel, "end");
        progressPanel.setVisible(false);
    }//GEN-LAST:event_acceptPurchase

    private void backToPersonalInfo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToPersonalInfo
        setDeliveryInfoCheckout();
        changeActivePurchaseStep("personalInfoDelivery", "payment");
        cl2.show(productPanel, "personalInfoDelivery");
    }//GEN-LAST:event_backToPersonalInfo

    private void backToPayment(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToPayment
        setPaymentInfoCheckout();
        changeActivePurchaseStep("payment", "accept");
        cl2.show(productPanel, "payment");
    }//GEN-LAST:event_backToPayment

    private void backToShoppingCart(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToShoppingCart
//        recommendedProductsPanel.setVisible(true);
//        changeActivePurchaseStep("shoppingCart", "personalInfoDelivery");
//        cl2.show(productPanel, "shoppingCart");
        
        cl2.show(productPanel, "shoppingCartPanel");
        cl.show(featurePanel, "checkout");

        progressPanel.setVisible(true);
        recommendedProductsPanel.setVisible(true);
    }//GEN-LAST:event_backToShoppingCart

    private void backToStore(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToStore
        refreshStartPage();
        cartContentsPanel.removeAll();
        cl.show(featurePanel, "cardStart");
        cl2.show(productPanel, "shoppingCart");
    }//GEN-LAST:event_backToStore

    private void lpFavoritesLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lpFavoritesLabelMouseClicked
        gpCon.getFavorites(this);
        displayFavorites();
        cl.show(featurePanel, "cardFavorites");
        
        
    }//GEN-LAST:event_lpFavoritesLabelMouseClicked

    private void lpDealsLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lpDealsLabelMouseClicked
        cl.show(featurePanel, "cardDeals");
    }//GEN-LAST:event_lpDealsLabelMouseClicked

    private void cartImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cartImageMouseClicked
        gpCon.sc.clear();
        gpCon.cpo.clear();
        cartContentsPanel.removeAll();
        gpCon.iMDH.shutDown();
    }//GEN-LAST:event_cartImageMouseClicked

    private void saveButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButton1ActionPerformed
        gpCon.iMDH.shutDown();
    }//GEN-LAST:event_saveButton1ActionPerformed

    private void orderHistoryListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_orderHistoryListValueChanged
                
                if (orderHistoryList.getSelectedIndex() >(-1)){
//                System.out.println("orderindex: " + orderHistoryList.getSelectedIndex());
                gpCon.getHistory(this, orderHistoryList.getSelectedIndex());
                historyItemShower.removeAll();
                displayHistory();
                }
    }//GEN-LAST:event_orderHistoryListValueChanged

    private void iMatLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iMatLabelMouseClicked
        refreshStartPage();
        cl.show(featurePanel, "cardStart");
    }//GEN-LAST:event_iMatLabelMouseClicked

    private void accountImageMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountImageMouseEntered
        accountImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iMatAvatar2hov.png")));
    }//GEN-LAST:event_accountImageMouseEntered

    private void accountImageMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountImageMouseExited
        accountImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/iMatAvatar2.png")));
    }//GEN-LAST:event_accountImageMouseExited

    private void accountImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_accountImageMouseClicked
        setValuesMyAccount();
        cl.show(featurePanel, "myAccount");
    }//GEN-LAST:event_accountImageMouseClicked

    private void addHistoryToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addHistoryToCartActionPerformed
//        System.out.println("ey" + orderHistoryList.getSelectedIndex());
        if (orderHistoryList.getSelectedIndex() == 0 || orderHistoryList.getSelectedIndex() > 0){
        gpCon.getHistory(this, orderHistoryList.getSelectedIndex());
        
        gpCon.addHistoryToCart(this);
    }
    }//GEN-LAST:event_addHistoryToCartActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Radera ordrar stöds ej av backend.
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(IMatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IMatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IMatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IMatFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IMatFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel acceptAddressLabel;
    private javax.swing.JLabel acceptCardHolderLabel;
    private javax.swing.JLabel acceptCardNumberLabel;
    private javax.swing.JLabel acceptCardTypeLabel;
    private javax.swing.JLabel acceptDeliveryLabel;
    private javax.swing.JLabel acceptDeliveryTime;
    private javax.swing.JLabel acceptNameLabel;
    private javax.swing.JPanel acceptPanel;
    private javax.swing.JLabel acceptPaymentLabel;
    private javax.swing.JLabel acceptPostCodeLabel;
    private javax.swing.JButton acceptPurchaseButton;
    private javax.swing.JLabel acceptSeparator1;
    private javax.swing.JLabel acceptSeparator3;
    private javax.swing.JLabel acceptSeparator4;
    private javax.swing.JLabel acceptShoppingCartLabel;
    private javax.swing.JLabel acceptValidMonthYearLabel;
    private javax.swing.JLabel accountImage;
    private javax.swing.JButton addHistoryToCart;
    private javax.swing.JTextField address1TextField;
    private javax.swing.JTextField address1TextField1;
    private javax.swing.JTextField address2TextField;
    private javax.swing.JTextField address2TextField1;
    private javax.swing.JPanel addressPanel;
    private javax.swing.JPanel addressPanel2;
    private javax.swing.JLabel adress1Label;
    private javax.swing.JLabel adress1Label1;
    private javax.swing.JLabel adress1Label2;
    private javax.swing.JLabel adress1Label3;
    private javax.swing.JLabel adress2Label;
    private javax.swing.JLabel adress2Label1;
    private javax.swing.JLabel adress2Label2;
    private javax.swing.JLabel adress2Label3;
    private javax.swing.JTextField adress2TextField1;
    private javax.swing.JTextField adress2TextField2;
    private javax.swing.JLabel arrow1Label;
    private javax.swing.JLabel arrow2Label;
    private javax.swing.JLabel arrow3Label;
    private javax.swing.JButton backButtonToPayment;
    private javax.swing.JButton backButtonToPersonalInfo;
    private javax.swing.JButton backButtonToShoppingCart;
    private javax.swing.JButton backToStoreButton;
    private javax.swing.JLabel cardHolderNameLabel;
    private javax.swing.JLabel cardHolderNameLabel1;
    private javax.swing.JTextField cardHolderTextField;
    private javax.swing.JLabel cardNumberLabel;
    private javax.swing.JLabel cardNumberLabel1;
    private javax.swing.JTextField cardNumberTextField;
    private javax.swing.JComboBox cardTypeComboBox;
    private javax.swing.JLabel cardTypeLabel;
    private javax.swing.JLabel cardTypeLabel1;
    private javax.swing.JPanel cartContentsPanel;
    private javax.swing.JLabel cartImage;
    private javax.swing.JPanel cartPanel;
    private javax.swing.JLabel cartPanelNbrOfItemsLabel;
    private javax.swing.JScrollPane cartPanelScrollPane;
    private javax.swing.JLabel cartPanelTotalCostLabel;
    private javax.swing.JLabel categoryFeatureLabel;
    private javax.swing.JPanel categoryFeaturePanel;
    private javax.swing.JPanel categoryPanel;
    private javax.swing.JPanel checkPaymentPanel;
    private javax.swing.JPanel checkPersonalInfoPanel;
    private javax.swing.JLabel checkoutAcceptLabel;
    private javax.swing.JTextField checkoutAddressTF;
    private javax.swing.JTextField checkoutCardHolderTF;
    private javax.swing.JTextField checkoutCardNumberTF;
    private javax.swing.JComboBox checkoutCardTypeCB;
    private javax.swing.JLabel checkoutDeliveryLabel;
    private javax.swing.JTextField checkoutEmailTF;
    private javax.swing.JTextField checkoutFirstNameTF;
    private javax.swing.JTextField checkoutLastNameTF;
    private javax.swing.JTextField checkoutOtherAddressTF;
    private javax.swing.JTextField checkoutOtherPostCodeTF;
    private javax.swing.JPanel checkoutPanel;
    private javax.swing.JLabel checkoutPaymentLabel;
    private javax.swing.JTextField checkoutPhoneNumberTF;
    private javax.swing.JTextField checkoutPostCodeTF;
    private javax.swing.JLabel checkoutShoppingCartLabel;
    private javax.swing.JComboBox checkoutValidMonthCB;
    private javax.swing.JComboBox checkoutValidYearCB;
    private javax.swing.JTextField checkoutVerificationCodeTF;
    private javax.swing.JLabel cityLabel;
    private javax.swing.JLabel cityLabel1;
    private javax.swing.JLabel cityLabel2;
    private javax.swing.JLabel cityLabel3;
    private javax.swing.JTextField cityTextField;
    private javax.swing.JTextField cityTextField1;
    private javax.swing.JTextField cityTextField2;
    private javax.swing.JTextField cityTextField3;
    private javax.swing.JLabel codeLabel1;
    private javax.swing.JLabel codeLabel2;
    private javax.swing.JLabel dealsLabel;
    private javax.swing.JPanel dealsViewPanel;
    private javax.swing.JLabel deliverToMyAddressLabel;
    private javax.swing.JLabel deliverToMyAddressLabel1;
    private javax.swing.JPanel deliverToMyAdressPanel;
    private javax.swing.JPanel deliverToMyAdressPanel1;
    private javax.swing.JCheckBox deliveryCheckBox;
    private javax.swing.JCheckBox deliveryCheckBox1;
    private javax.swing.JLabel deliveryDateLabel;
    private javax.swing.JLabel deliveryDateLabel1;
    private javax.swing.JComboBox deliveryDayComboBox;
    private javax.swing.JComboBox deliveryDayComboBox1;
    private javax.swing.JPanel deliveryPanel;
    private javax.swing.JPanel deliveryPanel1;
    private javax.swing.JComboBox deliveryTimeComboBox;
    private javax.swing.JComboBox deliveryTimeComboBox1;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel emailLabel1;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JPanel endOfCheckoutPanel;
    private javax.swing.JLabel expDateSlashLabel;
    private javax.swing.JLabel expDateSlashLabel1;
    private javax.swing.JLabel expirationDateLabel;
    private javax.swing.JLabel expirationDateLabel1;
    private javax.swing.JPanel featurePanel;
    private javax.swing.JLabel firstNameLabel;
    private javax.swing.JLabel firstNameLabel1;
    private javax.swing.JTextField firstNameTextField;
    private javax.swing.JLabel fpDealsLabel;
    private javax.swing.JLabel fpDealsLabel1;
    private javax.swing.JLabel fpHistoryLabel;
    private javax.swing.JLabel fpMyFavoritesLabel;
    private javax.swing.JLabel fpMyShoppingBagsLabel;
    private javax.swing.JLabel fpRecipesLabel;
    private javax.swing.JButton goToCashierBtn;
    private javax.swing.JButton historyBtn;
    private javax.swing.JPanel historyItemShower;
    private javax.swing.JLabel historyNbrOfItemsLabel;
    private javax.swing.JLabel historyNbrOfItemsPHLabel;
    private javax.swing.JLabel historyTotalCostPHLabel;
    private javax.swing.JLabel historyTotalCostlabel;
    private javax.swing.JPanel historyViewPanel;
    private javax.swing.JLabel iMatLabel;
    private javax.swing.JPanel itemShower;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JLabel krLabel;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JLabel lastNameLabel1;
    private javax.swing.JTextField lastNameTextField;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JPanel leftPanelSpecials;
    private javax.swing.JPanel leftUpperPanel;
    private javax.swing.JPanel logoPanel;
    private javax.swing.JLabel lpDealsLabel;
    private javax.swing.JLabel lpFavoritesLabel;
    private javax.swing.JPanel myAccountContentPanel;
    private javax.swing.JPanel myAccountPanel;
    private javax.swing.JPanel myAccountTopPanel;
    private javax.swing.JPanel myFavoritesDisplayPanel;
    private javax.swing.JPanel myFavoritesViewPanel;
    private javax.swing.JButton myShoppingBagsBtn;
    private javax.swing.JPanel myShoppingBagsViewPanel;
    private javax.swing.JButton nextStepButtonToAccept;
    private javax.swing.JButton nextStepButtonToPayment;
    private javax.swing.JButton nextStepButtonToPersonalInfo;
    private javax.swing.JSeparator optCatSeparator;
    private javax.swing.JList orderHistoryList;
    private javax.swing.JLabel paymentInfoLabel;
    private javax.swing.JLabel paymentInfoLabel1;
    private javax.swing.JPanel paymentInfoPanel;
    private javax.swing.JPanel paymentInfoPanel1;
    private javax.swing.JLabel personalInfoLabel;
    private javax.swing.JLabel personalInfoLabel1;
    private javax.swing.JLabel personalInfoLabel2;
    private javax.swing.JLabel personalInfoLabel3;
    private javax.swing.JPanel personalInfoPanel;
    private javax.swing.JPanel personalInfoPanel1;
    private javax.swing.JLabel phoneNumberLabel;
    private javax.swing.JLabel phoneNumberLabel1;
    private javax.swing.JTextField phoneNumberTextField;
    private javax.swing.JLabel popularLabel;
    private javax.swing.JLabel postCodeLabel;
    private javax.swing.JLabel postCodeLabel1;
    private javax.swing.JTextField postCodeTextField;
    private javax.swing.JTextField postCodeTextField1;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JPanel prodPanel;
    private javax.swing.JPanel productPanel;
    private javax.swing.JScrollPane productScrollPane;
    private javax.swing.JPanel progressPanel;
    private javax.swing.JPanel recProdsInsideScroll;
    private javax.swing.JLabel recentlyBoughtLabel;
    private javax.swing.JButton recipesBtn;
    private javax.swing.JPanel recipesViewPanel;
    private javax.swing.JLabel recommendedProductsLabel;
    private javax.swing.JPanel recommendedProductsPanel;
    private javax.swing.JScrollPane recommendedProductsScrollPane;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton saveButton1;
    private javax.swing.JButton saveButton2;
    private javax.swing.JTextField searchField;
    private javax.swing.JLabel searchFieldIcon;
    private javax.swing.JSeparator searchOptSeparator;
    private javax.swing.JPanel searchResultPanel;
    private javax.swing.JLabel separator1;
    private javax.swing.JLabel separator2;
    private javax.swing.JLabel separator3;
    private javax.swing.JLabel separator4;
    private javax.swing.JLabel separator5;
    private javax.swing.JLabel separator6;
    private javax.swing.JLabel separator7;
    private javax.swing.JLabel separator8;
    private javax.swing.JLabel separator9;
    private javax.swing.JPanel shoppingCartPanel;
    private javax.swing.JPanel startLatestOrdersDisplayPanel;
    private javax.swing.JPanel startViewPanel;
    private javax.swing.JLabel thanksLabel;
    private javax.swing.JLabel titleNameLabel;
    private javax.swing.JPanel topPanel;
    private javax.swing.JLabel totalPriceLabel;
    private javax.swing.JLabel tpCartContentsNumber;
    private javax.swing.JLabel tpCartSumLabel;
    private javax.swing.JLabel tpMyAccountLabel;
    private javax.swing.JComboBox validMonthComboBox;
    private javax.swing.JComboBox validYearComboBox;
    private javax.swing.JLabel verificationCodeLabel;
    private javax.swing.JLabel verificationCodeLabel1;
    private javax.swing.JTextField verificationCodeTextField;
    // End of variables declaration//GEN-END:variables

    void updateCartPanel(ArrayList<CartProdObject> cpo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
