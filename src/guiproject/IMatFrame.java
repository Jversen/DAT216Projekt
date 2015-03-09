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
import se.chalmers.ait.dat215.project.ProductCategory;
import se.chalmers.ait.dat215.project.ShoppingItem;

public class IMatFrame extends javax.swing.JFrame implements ActionListener {


    /**
     * prefix för objekt:
     * lp = leftPanel
     * tp = topPanel
     * fp = featurePanel (mittenpanel)
     *
     * ex: lpRecipesLabel = label "Recept" i vänsterpanelen
     */

    /** Skapar controller från GUIProject.java
     */
    GUIProject gpCon;
    private CardLayout cl;
    private CardLayout cl2;

    private final AddressPanel addressPanel1 = new AddressPanel();

    // Remove
    private final ImageIcon saveButtonNormal = new ImageIcon(getClass().getResource("/greenButton.png"));
    private final ImageIcon saveButtonHover = new ImageIcon(getClass().getResource("/greenButtonHover.png"));
    private final ImageIcon saveButtonClick = new ImageIcon(getClass().getResource("/greenButtonClick.png"));
    // ----
    
    // Icons for the steps in the purchase.
    private final ImageIcon purchaseStep = new ImageIcon(getClass().getResource("/purchaseStep.png"));
    private final ImageIcon purchaseStepActive = new ImageIcon(getClass().getResource("/purchaseStepActive.png"));
    
    /**
     * Skapar arraylist för matkategorierna och deras underkategorier.
     */
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
        gpCon.createAllProducts();
        for (int i = 0; i<gpCon.allProducts.size(); i++){
            allProductCards.add(new ProductCard(gpCon.allProducts.get(i), this));
        }
    }
    
    public void searchFieldDynSearch(){
    gpCon.doSearch(searchField.getText(), this);
        displayGroceries();
    }
    
    public void updateCartPanel(List<CartProdObject> cpolist){

        cartContentsPanel.removeAll();
        System.out.println(cpolist.size());
       for (CartProdObject cpo : cpolist) {
           System.out.println("hello");
           System.out.println(cpo.toString());
           cartContentsPanel.add(cpo);
       }
        System.out.println("updateCartPanel cpo: " + cpolist);

                revalidate();
                repaint();
                System.out.print("Updating cart panel! "+ cartContentsPanel.getComponentCount() + "gpCon"+cpolist.size());
    }
    
    private void redrawCategories(){
        
        categoryPanel.removeAll();
        allCategoryArrayList.clear();
        for(int i = 0; i<headCategoryArrayList.size(); i++){
            allCategoryArrayList.add(headCategoryArrayList.get(i));
        }
        
    }
    
    private void displayGroceries(){

        GridLayout layout = new GridLayout(gpCon.products.size(),1);

        itemShower.setLayout(layout);
        itemShower.removeAll();
        if (gpCon.products.size()==0){
            JLabel noSearchResults = new JLabel("Inga sökresultat på " + searchField.getText());
            itemShower.add(noSearchResults);
        }
        for (int i = 0; i < gpCon.productCards.size(); i++) {
            itemShower.add(gpCon.productCards.get(i));
        }

        cl.show(featurePanel, "searchResultPanel");
        revalidate();
        repaint();
    }

    public void displayCart(List<CartProdObject> cpolist){
                cartContentsPanel.removeAll();
                for (int i = 0; i < cpolist.size(); i++) {
                    cartContentsPanel.add(cpolist.get(i));
                }
    }
    
    // Changes the active/current purchase step.
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
    
    // Sets the progress bar as visible in the purchase.
    public void showProgressBar(){
        checkoutShoppingCartLabel.show();
        checkoutDeliveryLabel.show();
        checkoutPaymentLabel.show();
        checkoutAcceptLabel.show();
        
        arrow1Label.show();
        arrow2Label.show();
        arrow3Label.show();
    }
    
    // Sets the progress bar as invisible in the purchase.
    public void hideProgressBar(){
        checkoutShoppingCartLabel.hide();
        checkoutDeliveryLabel.hide();
        checkoutPaymentLabel.hide();
        checkoutAcceptLabel.hide();
        
        arrow1Label.hide();
        arrow2Label.hide();
        arrow3Label.hide();
    }
    
    // Sets the recommended products panel as visible in the purchase.
    public void showRecommendedProducts(){
        recommendedProductsLabel.show();
        recommendedProductsScrollPane.show();
    }
    
    // Sets the recommended products panel as invisible in the purchase.
    public void hideRecommendedProducts(){
        recommendedProductsLabel.hide();
        recommendedProductsScrollPane.hide();
    }

    /**
     * Creates new form iMatFrame
     */

    public IMatFrame() {
        initComponents();
        this.gpCon = new GUIProject();
        
        cl = (CardLayout)featurePanel.getLayout();
        cl2 = (CardLayout)productPanel.getLayout();
       
        deliveryPanel.add(addressPanel1);
        createAllProductCards();
        /**
         * Skapar label för varje matkategori.
         * Skapa eventuellt JButtons istället för JLabels senare.
         */

        /**Kategorier och underkategorier:
         *
         * Frukt och grönt
         *  -Baljväxter
         *  -Bär
         *  -Citrusfrukter
         *  -Exotiska frukter
         *  -Grönsaksfrukter
         *  -Sallad
         *  -Meloner
         *  -Nötter och frön?
         *  -Frukter
         *  -Rotfrukter
         *
         * Bröd och torrvaror
         *  -Bröd
         *  -Mjöl, socker och salt
         *  -Pasta
         *  -Potatis och ris
         *
         * Kryddor
         *  -Örter
         *
         * Drycker
         *  -Varma drycker
         *  -Kalla drycker
         *
         * Mejeriprodukter
         *
         * Kött
         *
         * Fisk
         *
         * Sötsaker
         */



        JLabel headCategoryBread = new JLabel("Bröd och torrvaror");
        headCategoryArrayList.add(headCategoryBread);

            JLabel subCategoryBreadLabel = new JLabel("Bröd");
            JLabel subCategoryFlourSugarSaltLabel = new JLabel("Mjöl, socker och salt");
            JLabel subCategoryPastaLabel = new JLabel("Pasta");
            JLabel subCategoryPotatoRiceLabel = new JLabel("Potatis och ris");

        breadCategoryArrayList.add(subCategoryBreadLabel);
        breadCategoryArrayList.add(subCategoryFlourSugarSaltLabel);
        breadCategoryArrayList.add(subCategoryPastaLabel);
        breadCategoryArrayList.add(subCategoryPotatoRiceLabel);

        for (int i = 0; i<breadCategoryArrayList.size(); i++){
            breadCategoryArrayList.get(i).setBackground(Color.white);
            breadCategoryArrayList.get(i).setOpaque(true);
            breadCategoryArrayList.get(i).addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                saveButtonSaveButtonHover(evt);
            }
        });
        }

        JLabel headCategoryDrinks = new JLabel("Drycker");
        headCategoryArrayList.add(headCategoryDrinks);

            JLabel subCategoryHotDrinksLabel = new JLabel("Varma drycker");
            JLabel subCategoryColdDrinksLabel = new JLabel("Kalla drycker");

        drinkCategoryArrayList.add(subCategoryHotDrinksLabel);
        drinkCategoryArrayList.add(subCategoryColdDrinksLabel);

        for (int i = 0; i<drinkCategoryArrayList.size(); i++){
            drinkCategoryArrayList.get(i).setBackground(Color.white);
            drinkCategoryArrayList.get(i).setOpaque(true);
        }

        JLabel headCategoryFish = new JLabel("Fisk");
        headCategoryArrayList.add(headCategoryFish);

            JLabel subCategoryFishLabel = new JLabel("Fisk");

        fishCategoryArrayList.add(subCategoryFishLabel);

        for (int i = 0; i<fishCategoryArrayList.size(); i++){
            fishCategoryArrayList.get(i).setBackground(Color.white);
            fishCategoryArrayList.get(i).setOpaque(true);
        }

        JLabel headCategoryFruitsAndVegs = new JLabel("Frukt och grönt");
        headCategoryArrayList.add(headCategoryFruitsAndVegs);


            JLabel subCategoryPodLabel = new JLabel("Baljväxter");
            JLabel subCategoryBerryLabel = new JLabel("Bär");
            JLabel subCategoryCitrusFruitLabel = new JLabel("Citrusfrukter");
            JLabel subCategoryExoticFruitLabel = new JLabel("Exotiska frukter");
            JLabel subCategoryFruitLabel = new JLabel("Frukt");
            JLabel subCategoryVegetableFruitLabel = new JLabel("Grönsaksfrukter");
            JLabel subCategoryMelonsLabel = new JLabel("Meloner");
            JLabel subCategoryNutsAndSeedsLabel = new JLabel("Nötter och frön");
            JLabel subCategoryRootVegetableLabel = new JLabel("Rotfrukter");
            JLabel subCategoryCabbageLabel = new JLabel("Kål");

        fruitVegsCategoryArrayList.add(subCategoryPodLabel);
        fruitVegsCategoryArrayList.add(subCategoryBerryLabel);
        fruitVegsCategoryArrayList.add(subCategoryCitrusFruitLabel);
        fruitVegsCategoryArrayList.add(subCategoryExoticFruitLabel);
        fruitVegsCategoryArrayList.add(subCategoryFruitLabel);
        fruitVegsCategoryArrayList.add(subCategoryVegetableFruitLabel);
        fruitVegsCategoryArrayList.add(subCategoryMelonsLabel);
        fruitVegsCategoryArrayList.add(subCategoryNutsAndSeedsLabel);
        fruitVegsCategoryArrayList.add(subCategoryRootVegetableLabel);
        fruitVegsCategoryArrayList.add(subCategoryCabbageLabel);

        for (int i = 0; i<fruitVegsCategoryArrayList.size(); i++){
            fruitVegsCategoryArrayList.get(i).setBackground(Color.white);
            fruitVegsCategoryArrayList.get(i).setOpaque(true);
        }

        JLabel headCategorySpice = new JLabel("Kryddor");
        headCategoryArrayList.add(headCategorySpice);

            JLabel subCategoryHerbLabel = new JLabel("Örter");
        spiceCategoryArrayList.add(subCategoryHerbLabel);

        for (int i = 0; i<spiceCategoryArrayList.size(); i++){
            spiceCategoryArrayList.get(i).setBackground(Color.white);
            spiceCategoryArrayList.get(i).setOpaque(true);
        }

        JLabel headCategoryMeat = new JLabel("Kött");
        headCategoryArrayList.add(headCategoryMeat);

            JLabel subCategoryMeatLabel = new JLabel("Kött");
        meatCategoryArrayList.add(subCategoryMeatLabel);

        for (int i = 0; i<meatCategoryArrayList.size(); i++){
            meatCategoryArrayList.get(i).setBackground(Color.white);
            meatCategoryArrayList.get(i).setOpaque(true);
        }

        JLabel headCategoryDairies = new JLabel("Mejeriprodukter");
        headCategoryArrayList.add(headCategoryDairies);

            JLabel category12Label = new JLabel("Mejeriprodukter");
        dairiesCategoryArrayList.add(category12Label);

        for (int i = 0; i<dairiesCategoryArrayList.size(); i++){
            dairiesCategoryArrayList.get(i).setBackground(Color.white);
            dairiesCategoryArrayList.get(i).setOpaque(true);
        }

        JLabel headCategorySweets = new JLabel("Sötsaker");
        headCategoryArrayList.add(headCategorySweets);

        JLabel category20Label = new JLabel("Sötsaker");
        sweetsCategoryArrayList.add(category20Label);

        for (int i = 0; i<sweetsCategoryArrayList.size(); i++){
            sweetsCategoryArrayList.get(i).setBackground(Color.white);
            sweetsCategoryArrayList.get(i).setOpaque(true);
        }

        allSubCategoryArrayList.addAll(breadCategoryArrayList);
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
    private void categoryLabelMouseClicked(java.awt.event.MouseEvent evt) {   

        JLabel tempLabel1 = (JLabel)evt.getSource();

        categoryStrings.clear();
        
        switch(tempLabel1.getText()){
            case "Bröd och torrvaror": 
                redrawCategories();
                
                categoryStrings.add("BREAD");
                categoryStrings.add("FLOUR_SUGAR_SALT");
                categoryStrings.add("PASTA");
                categoryStrings.add("POTATO_RICE");
                
                for (int i = 0; i<categoryStrings.size(); i++){
                    System.out.println(categoryStrings.get(i).toString());
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
                    System.out.println(categoryStrings.get(i).toString());
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
                    System.out.println(categoryStrings.get(i).toString());
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
            case "Baljväxter":
                categoryStrings.add("POD");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "Bröd":
                categoryStrings.add("BREAD");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "Bär":
                categoryStrings.add("BERRY");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "Citrusfrukter":
                categoryStrings.add("CITRUS_FRUIT");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
                
            case "Varma drycker":
                categoryStrings.add("HOT_DRINKS");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "Kalla drycker":
                categoryStrings.add("COLD_DRINKS");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
                
            case "Exotiska frukter":
                categoryStrings.add("EXOTIC_FRUIT");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "Fisk":
                categoryStrings.add("FISH");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
                
            case "Grönsaksfrukter":
                categoryStrings.add("VEGETABLE_FRUIT");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "Kål":
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
            case "Meloner":
                categoryStrings.add("MELONS");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
           
            case "Mjöl, socker och salt":
                categoryStrings.add("FLOUR_SUGAR_SALT");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "Nötter och frön":
                categoryStrings.add("NUTS_AND_SEEDS");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "Pasta":
                categoryStrings.add("PASTA");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "Potatis och ris":
                categoryStrings.add("POTATO_RICE");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "Rotfrukter":
                categoryStrings.add("ROOT_VEGETABLE");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
             
            case "Frukt":
                categoryStrings.add("FRUIT");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "Sötsaker":
                categoryStrings.add("SWEET");
                gpCon.listCatProds(categoryStrings, this);
                displayGroceries();
                break;
            case "Örter":
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        updCartBtn = new javax.swing.JButton();
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
        myShoppingBagsViewPanel = new javax.swing.JPanel();
        fpMyShoppingBagsLabel = new javax.swing.JLabel();
        myFavoritesViewPanel = new javax.swing.JPanel();
        fpMyFavoritesLabel = new javax.swing.JLabel();
        recipesViewPanel = new javax.swing.JPanel();
        fpRecipesLabel = new javax.swing.JLabel();
        dealsViewPanel = new javax.swing.JPanel();
        fpDealsLabel = new javax.swing.JLabel();
        historyViewPanel = new javax.swing.JPanel();
        fpHistoryLabel = new javax.swing.JLabel();
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
        saveButton = new javax.swing.JButton();
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
        recommendedProductsPanel = new javax.swing.JPanel();
        recommendedProductsLabel = new javax.swing.JLabel();
        recommendedProductsScrollPane = new javax.swing.JScrollPane();
        productPanel = new javax.swing.JPanel();
        shoppingCartPanel = new javax.swing.JPanel();
        productScrollPane = new javax.swing.JScrollPane();
        nextStepButtonToPersonalInfo = new javax.swing.JButton();
        totalPriceLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        krLabel = new javax.swing.JLabel();
        checkPersonalInfoPanel = new javax.swing.JPanel();
        backButtonToShoppingCart = new javax.swing.JButton();
        nextStepButtonToPayment = new javax.swing.JButton();
        personalInfoPanel1 = new javax.swing.JPanel();
        personalInfoLabel2 = new javax.swing.JLabel();
        firstNameLabel1 = new javax.swing.JLabel();
        cashierFirstNameTF = new javax.swing.JTextField();
        cashierLastNameTF = new javax.swing.JTextField();
        lastNameLabel1 = new javax.swing.JLabel();
        cashierAddressTF = new javax.swing.JTextField();
        adress1Label2 = new javax.swing.JLabel();
        address2TextField1 = new javax.swing.JTextField();
        adress2Label2 = new javax.swing.JLabel();
        postCodeLabel1 = new javax.swing.JLabel();
        cashierPostCodeTF = new javax.swing.JTextField();
        cityLabel2 = new javax.swing.JLabel();
        cityTextField2 = new javax.swing.JTextField();
        emailLabel1 = new javax.swing.JLabel();
        cashierEmailTF = new javax.swing.JTextField();
        separator4 = new javax.swing.JLabel();
        cashierPhoneNumberTF = new javax.swing.JTextField();
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
        cashierOtherAddressTF = new javax.swing.JTextField();
        adress2TextField2 = new javax.swing.JTextField();
        adress2Label3 = new javax.swing.JLabel();
        codeLabel2 = new javax.swing.JLabel();
        cashierOtherPostCodeTF = new javax.swing.JTextField();
        cityTextField3 = new javax.swing.JTextField();
        cityLabel3 = new javax.swing.JLabel();
        saveButton1 = new javax.swing.JButton();
        checkPaymentPanel = new javax.swing.JPanel();
        backButtonToPersonalInfo = new javax.swing.JButton();
        nextStepButtonToAccept = new javax.swing.JButton();
        paymentInfoPanel1 = new javax.swing.JPanel();
        paymentInfoLabel1 = new javax.swing.JLabel();
        cardNumberLabel1 = new javax.swing.JLabel();
        cardNumberTextField1 = new javax.swing.JTextField();
        cardTypeComboBox1 = new javax.swing.JComboBox();
        cardTypeLabel1 = new javax.swing.JLabel();
        expirationDateLabel1 = new javax.swing.JLabel();
        validMonthComboBox1 = new javax.swing.JComboBox();
        expDateSlashLabel1 = new javax.swing.JLabel();
        validYearComboBox1 = new javax.swing.JComboBox();
        verificationCodeLabel1 = new javax.swing.JLabel();
        verificationCodeTextField1 = new javax.swing.JTextField();
        cardHolderNameLabel1 = new javax.swing.JLabel();
        cardHolderTextField1 = new javax.swing.JTextField();
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
        acceptVerificationCodeLabel = new javax.swing.JLabel();
        acceptCardHolderLabel = new javax.swing.JLabel();
        endOfCheckoutPanel = new javax.swing.JPanel();
        thanksLabel = new javax.swing.JLabel();
        backToStoreButton = new javax.swing.JButton();
        cartPanel = new javax.swing.JPanel();
        goToCashierBtn = new javax.swing.JButton();
        cartPanelScrollPane = new javax.swing.JScrollPane();
        cartContentsPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tpCartContentsNumber = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tpCartSumLabel = new javax.swing.JLabel();
        cartImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 204, 204));

        leftPanel.setBackground(new java.awt.Color(215, 173, 173));
        leftPanel.setToolTipText("");

        leftUpperPanel.setBackground(new java.awt.Color(255, 248, 248));
        leftUpperPanel.setLayout(new java.awt.BorderLayout());

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
        lpFavoritesLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lpFavoritesLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lpFavoritesLabelMouseClicked(evt);
            }
        });
        leftPanelSpecials.add(lpFavoritesLabel);

        lpDealsLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lpDealsLabel.setText("Erbjudanden");
        lpDealsLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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
                .addComponent(categoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 701, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        topPanel.setBackground(new java.awt.Color(179, 62, 62));

        tpMyAccountLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tpMyAccountLabel.setText("Mitt konto");
        tpMyAccountLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tpMyAccountLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tpMyAccountLabelMouseClicked(evt);
            }
        });

        accountImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guiProfile.png"))); // NOI18N
        accountImage.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        myShoppingBagsBtn.setText("Mina matkassar");
        myShoppingBagsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myShoppingBagsBtnActionPerformed(evt);
            }
        });

        recipesBtn.setText("Recept");
        recipesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recipesBtnActionPerformed(evt);
            }
        });

        historyBtn.setText("Historik");
        historyBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                historyBtnActionPerformed(evt);
            }
        });

        updCartBtn.setText("Uppdatera varukorg");
        updCartBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updCartBtnActionPerformed(evt);
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
        iMatLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

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
                .addGap(18, 18, 18)
                .addComponent(myShoppingBagsBtn)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(updCartBtn))
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addGap(97, 97, 97)
                        .addComponent(recipesBtn)
                        .addGap(113, 113, 113)
                        .addComponent(historyBtn)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(accountImage)
                .addGap(18, 18, 18)
                .addComponent(tpMyAccountLabel)
                .addGap(55, 55, 55))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(logoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(topPanelLayout.createSequentialGroup()
                                .addComponent(updCartBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(myShoppingBagsBtn)
                                    .addComponent(recipesBtn)
                                    .addComponent(historyBtn)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, topPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(accountImage)
                            .addGroup(topPanelLayout.createSequentialGroup()
                                .addComponent(tpMyAccountLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(38, 38, 38)))
                        .addGap(4, 4, 4)))
                .addGap(49, 49, 49))
        );

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

        javax.swing.GroupLayout startViewPanelLayout = new javax.swing.GroupLayout(startViewPanel);
        startViewPanel.setLayout(startViewPanelLayout);
        startViewPanelLayout.setHorizontalGroup(
            startViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(startViewPanelLayout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addGroup(startViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(separator9)
                    .addComponent(separator7)
                    .addComponent(separator6)
                    .addComponent(dealsLabel)
                    .addComponent(recentlyBoughtLabel)
                    .addComponent(popularLabel))
                .addContainerGap(444, Short.MAX_VALUE))
        );
        startViewPanelLayout.setVerticalGroup(
            startViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(startViewPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(recentlyBoughtLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(127, 127, 127)
                .addComponent(popularLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(145, 145, 145)
                .addComponent(dealsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(separator9, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(373, Short.MAX_VALUE))
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
                .addGap(0, 682, Short.MAX_VALUE))
        );
        myShoppingBagsViewPanelLayout.setVerticalGroup(
            myShoppingBagsViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myShoppingBagsViewPanelLayout.createSequentialGroup()
                .addComponent(fpMyShoppingBagsLabel)
                .addGap(0, 776, Short.MAX_VALUE))
        );

        featurePanel.add(myShoppingBagsViewPanel, "cardBags");

        fpMyFavoritesLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        fpMyFavoritesLabel.setText("Mina favoriter");

        javax.swing.GroupLayout myFavoritesViewPanelLayout = new javax.swing.GroupLayout(myFavoritesViewPanel);
        myFavoritesViewPanel.setLayout(myFavoritesViewPanelLayout);
        myFavoritesViewPanelLayout.setHorizontalGroup(
            myFavoritesViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myFavoritesViewPanelLayout.createSequentialGroup()
                .addComponent(fpMyFavoritesLabel)
                .addGap(0, 702, Short.MAX_VALUE))
        );
        myFavoritesViewPanelLayout.setVerticalGroup(
            myFavoritesViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myFavoritesViewPanelLayout.createSequentialGroup()
                .addComponent(fpMyFavoritesLabel)
                .addGap(0, 776, Short.MAX_VALUE))
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
                .addGap(0, 776, Short.MAX_VALUE))
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
                .addGap(0, 776, Short.MAX_VALUE))
        );

        featurePanel.add(dealsViewPanel, "cardDeals");

        fpHistoryLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        fpHistoryLabel.setText("Historik");

        javax.swing.GroupLayout historyViewPanelLayout = new javax.swing.GroupLayout(historyViewPanel);
        historyViewPanel.setLayout(historyViewPanelLayout);
        historyViewPanelLayout.setHorizontalGroup(
            historyViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(historyViewPanelLayout.createSequentialGroup()
                .addComponent(fpHistoryLabel)
                .addGap(0, 770, Short.MAX_VALUE))
        );
        historyViewPanelLayout.setVerticalGroup(
            historyViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(historyViewPanelLayout.createSequentialGroup()
                .addComponent(fpHistoryLabel)
                .addContainerGap(776, Short.MAX_VALUE))
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
                .addContainerGap(780, Short.MAX_VALUE))
        );

        featurePanel.add(categoryFeaturePanel, "categoryFeaturePanel");

        searchResultPanel.setBackground(new java.awt.Color(255, 248, 248));
        searchResultPanel.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(255, 248, 248));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        itemShower.setLayout(new java.awt.GridLayout(1, 2));
        jPanel1.add(itemShower, new java.awt.GridBagConstraints());

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
        saveButton.addMouseListener(new java.awt.event.MouseAdapter() {
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
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

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
            public void mouseExited(java.awt.event.MouseEvent evt) {
                saveButtonSaveButtonUnhover(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                saveButtonSaveButtonHover(evt);
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
                            .addComponent(personalInfoLabel1)
                            .addComponent(saveButton, javax.swing.GroupLayout.Alignment.TRAILING))
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
                .addGap(28, 28, 28)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
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

        javax.swing.GroupLayout myAccountContentPanelLayout = new javax.swing.GroupLayout(myAccountContentPanel);
        myAccountContentPanel.setLayout(myAccountContentPanelLayout);
        myAccountContentPanelLayout.setHorizontalGroup(
            myAccountContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, myAccountContentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(personalInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(paymentInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(111, Short.MAX_VALUE))
        );
        myAccountContentPanelLayout.setVerticalGroup(
            myAccountContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myAccountContentPanelLayout.createSequentialGroup()
                .addComponent(paymentInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 137, Short.MAX_VALUE))
            .addGroup(myAccountContentPanelLayout.createSequentialGroup()
                .addComponent(personalInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

        recommendedProductsPanel.setBackground(new java.awt.Color(255, 255, 255));

        recommendedProductsLabel.setFont(new java.awt.Font("Gautami", 0, 18)); // NOI18N
        recommendedProductsLabel.setForeground(new java.awt.Color(102, 102, 102));
        recommendedProductsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        recommendedProductsLabel.setText("Glömt något?");
        recommendedProductsLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        recommendedProductsLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        recommendedProductsScrollPane.setBorder(null);

        javax.swing.GroupLayout recommendedProductsPanelLayout = new javax.swing.GroupLayout(recommendedProductsPanel);
        recommendedProductsPanel.setLayout(recommendedProductsPanelLayout);
        recommendedProductsPanelLayout.setHorizontalGroup(
            recommendedProductsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recommendedProductsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(recommendedProductsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, recommendedProductsPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(recommendedProductsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        recommendedProductsPanelLayout.setVerticalGroup(
            recommendedProductsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recommendedProductsPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(recommendedProductsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(recommendedProductsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        productPanel.setBackground(new java.awt.Color(204, 204, 255));
        productPanel.setLayout(new java.awt.CardLayout());

        shoppingCartPanel.setBackground(new java.awt.Color(255, 255, 255));

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

        javax.swing.GroupLayout shoppingCartPanelLayout = new javax.swing.GroupLayout(shoppingCartPanel);
        shoppingCartPanel.setLayout(shoppingCartPanelLayout);
        shoppingCartPanelLayout.setHorizontalGroup(
            shoppingCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shoppingCartPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nextStepButtonToPersonalInfo)
                .addContainerGap())
            .addGroup(shoppingCartPanelLayout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(shoppingCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, shoppingCartPanelLayout.createSequentialGroup()
                        .addComponent(totalPriceLabel)
                        .addGap(18, 18, 18)
                        .addComponent(priceLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(krLabel))
                    .addComponent(productScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        shoppingCartPanelLayout.setVerticalGroup(
            shoppingCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shoppingCartPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(productScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(shoppingCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalPriceLabel)
                    .addComponent(priceLabel)
                    .addComponent(krLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nextStepButtonToPersonalInfo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        productPanel.add(shoppingCartPanel, "shoppingCart");

        checkPersonalInfoPanel.setBackground(new java.awt.Color(255, 255, 255));

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
                            .addComponent(cashierAddressTF, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(personalInfoPanel1Layout.createSequentialGroup()
                            .addComponent(lastNameLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cashierLastNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(personalInfoPanel1Layout.createSequentialGroup()
                            .addComponent(firstNameLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cashierFirstNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                .addComponent(cashierPostCodeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cityTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cashierEmailTF, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(separator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, personalInfoPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(phoneNumberLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cashierPhoneNumberTF, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
                    .addComponent(cashierFirstNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lastNameLabel1)
                    .addComponent(cashierLastNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adress1Label2)
                    .addComponent(cashierAddressTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adress2Label2)
                    .addComponent(address2TextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(postCodeLabel1)
                    .addComponent(cashierPostCodeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cityLabel2)
                    .addComponent(cityTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailLabel1)
                    .addComponent(cashierEmailTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(personalInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cashierPhoneNumberTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(phoneNumberLabel1))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        deliveryPanel1.setBackground(new java.awt.Color(255, 255, 255));

        personalInfoLabel3.setFont(new java.awt.Font("Gautami", 0, 18)); // NOI18N
        personalInfoLabel3.setForeground(new java.awt.Color(51, 51, 51));
        personalInfoLabel3.setText("Leverans");
        personalInfoLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        deliveryDateLabel1.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        deliveryDateLabel1.setForeground(new java.awt.Color(153, 153, 153));
        deliveryDateLabel1.setText("Leveranstid");

        deliveryTimeComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "10:00-12:00", "12:00-14:00", "14:00-16:00", "16:00-18:00", "18:00-20:00", "20:00-22:00" }));
        deliveryTimeComboBox1.setSelectedIndex(1);
        deliveryTimeComboBox1.setToolTipText("");
        deliveryTimeComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        deliveryTimeComboBox1.setEnabled(false);

        deliveryDayComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "idag", "imorgon", "i övermorgon" }));
        deliveryDayComboBox1.setSelectedIndex(1);
        deliveryDayComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        deliveryDayComboBox1.setEnabled(false);

        deliverToMyAdressPanel1.setBackground(new java.awt.Color(255, 255, 255));

        deliverToMyAddressLabel1.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        deliverToMyAddressLabel1.setForeground(new java.awt.Color(153, 153, 153));
        deliverToMyAddressLabel1.setText("Leverera till annan adress");

        deliveryCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        deliveryCheckBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
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

        adress2TextField2.setEditable(false);
        adress2TextField2.setBackground(new java.awt.Color(204, 204, 204));

        adress2Label3.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        adress2Label3.setForeground(new java.awt.Color(153, 153, 153));
        adress2Label3.setText("Adress 2");

        codeLabel2.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        codeLabel2.setForeground(new java.awt.Color(51, 51, 51));
        codeLabel2.setText("Postnummer");

        cityTextField3.setEditable(false);
        cityTextField3.setBackground(new java.awt.Color(204, 204, 204));

        cityLabel3.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        cityLabel3.setForeground(new java.awt.Color(153, 153, 153));
        cityLabel3.setText("Ort");

        javax.swing.GroupLayout addressPanel2Layout = new javax.swing.GroupLayout(addressPanel2);
        addressPanel2.setLayout(addressPanel2Layout);
        addressPanel2Layout.setHorizontalGroup(
            addressPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addressPanel2Layout.createSequentialGroup()
                .addComponent(adress1Label3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cashierOtherAddressTF, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addressPanel2Layout.createSequentialGroup()
                .addComponent(adress2Label3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(adress2TextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addressPanel2Layout.createSequentialGroup()
                .addComponent(codeLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cashierOtherPostCodeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                    .addComponent(cashierOtherAddressTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addressPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adress2Label3)
                    .addComponent(adress2TextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addressPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codeLabel2)
                    .addComponent(cashierOtherPostCodeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(addressPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cityLabel3)
                    .addComponent(cityTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

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
                    .addComponent(addressPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
                .addContainerGap(82, Short.MAX_VALUE))
        );

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

        javax.swing.GroupLayout checkPersonalInfoPanelLayout = new javax.swing.GroupLayout(checkPersonalInfoPanel);
        checkPersonalInfoPanel.setLayout(checkPersonalInfoPanelLayout);
        checkPersonalInfoPanelLayout.setHorizontalGroup(
            checkPersonalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkPersonalInfoPanelLayout.createSequentialGroup()
                .addComponent(personalInfoPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deliveryPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, checkPersonalInfoPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(checkPersonalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, checkPersonalInfoPanelLayout.createSequentialGroup()
                        .addComponent(saveButton1)
                        .addGap(124, 124, 124))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, checkPersonalInfoPanelLayout.createSequentialGroup()
                        .addComponent(backButtonToShoppingCart)
                        .addGap(18, 18, 18)
                        .addComponent(nextStepButtonToPayment)
                        .addContainerGap())))
        );
        checkPersonalInfoPanelLayout.setVerticalGroup(
            checkPersonalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkPersonalInfoPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(checkPersonalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(personalInfoPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(deliveryPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(saveButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addGroup(checkPersonalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nextStepButtonToPayment)
                    .addComponent(backButtonToShoppingCart))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        cardTypeComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "VISA", "MasterCard", "PayPal" }));
        cardTypeComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        cardTypeLabel1.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        cardTypeLabel1.setForeground(new java.awt.Color(51, 51, 51));
        cardTypeLabel1.setText("Korttyp");

        expirationDateLabel1.setFont(new java.awt.Font("Gautami", 0, 14)); // NOI18N
        expirationDateLabel1.setForeground(new java.awt.Color(51, 51, 51));
        expirationDateLabel1.setText("Expiration date");

        validMonthComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        validMonthComboBox1.setSelectedIndex(5);
        validMonthComboBox1.setToolTipText("Månad");
        validMonthComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        expDateSlashLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        expDateSlashLabel1.setForeground(new java.awt.Color(51, 51, 51));
        expDateSlashLabel1.setText("/");

        validYearComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2015", "2016", "2017", "2018", "2019", "2020" }));
        validYearComboBox1.setToolTipText("År");
        validYearComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

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
                                .addComponent(cardTypeComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cardNumberTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(paymentInfoPanel1Layout.createSequentialGroup()
                                    .addComponent(validMonthComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(expDateSlashLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(validYearComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(verificationCodeTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(paymentInfoPanel1Layout.createSequentialGroup()
                            .addComponent(cardHolderNameLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(cardHolderTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(cardNumberTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paymentInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cardTypeComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cardTypeLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paymentInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(expirationDateLabel1)
                    .addComponent(validMonthComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(validYearComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(expDateSlashLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paymentInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(verificationCodeTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(verificationCodeLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(paymentInfoPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cardHolderNameLabel1)
                    .addComponent(cardHolderTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout checkPaymentPanelLayout = new javax.swing.GroupLayout(checkPaymentPanel);
        checkPaymentPanel.setLayout(checkPaymentPanelLayout);
        checkPaymentPanelLayout.setHorizontalGroup(
            checkPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, checkPaymentPanelLayout.createSequentialGroup()
                .addContainerGap(532, Short.MAX_VALUE)
                .addComponent(backButtonToPersonalInfo)
                .addGap(18, 18, 18)
                .addComponent(nextStepButtonToAccept)
                .addContainerGap())
            .addGroup(checkPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(checkPaymentPanelLayout.createSequentialGroup()
                    .addGap(167, 167, 167)
                    .addComponent(paymentInfoPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(166, Short.MAX_VALUE)))
        );
        checkPaymentPanelLayout.setVerticalGroup(
            checkPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, checkPaymentPanelLayout.createSequentialGroup()
                .addContainerGap(436, Short.MAX_VALUE)
                .addGroup(checkPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButtonToPersonalInfo)
                    .addComponent(nextStepButtonToAccept))
                .addGap(72, 72, 72))
            .addGroup(checkPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(checkPaymentPanelLayout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addComponent(paymentInfoPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(285, Short.MAX_VALUE)))
        );

        productPanel.add(checkPaymentPanel, "payment");

        acceptPanel.setBackground(new java.awt.Color(255, 255, 255));

        backButtonToPayment.setText("Tillbaka");
        backButtonToPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToPayment(evt);
            }
        });

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

        acceptVerificationCodeLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        acceptVerificationCodeLabel.setForeground(new java.awt.Color(51, 51, 51));
        acceptVerificationCodeLabel.setText("CVV");

        acceptCardHolderLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        acceptCardHolderLabel.setForeground(new java.awt.Color(51, 51, 51));
        acceptCardHolderLabel.setText("Kortinnehavare");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130, Short.MAX_VALUE)
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
                            .addComponent(acceptCardNumberLabel)
                            .addComponent(acceptCardTypeLabel)
                            .addComponent(acceptValidMonthYearLabel)
                            .addComponent(acceptVerificationCodeLabel)
                            .addComponent(acceptCardHolderLabel))
                        .addGap(101, 101, 101))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, acceptPanelLayout.createSequentialGroup()
                        .addComponent(backButtonToPayment)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acceptPurchaseButton)
                        .addContainerGap())))
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
                        .addGap(67, 67, 67)
                        .addComponent(acceptPaymentLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acceptSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acceptCardNumberLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acceptCardTypeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acceptValidMonthYearLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acceptVerificationCodeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(acceptCardHolderLabel))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 90, Short.MAX_VALUE)
                .addGroup(acceptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButtonToPayment)
                    .addComponent(acceptPurchaseButton))
                .addGap(71, 71, 71))
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
                .addGroup(endOfCheckoutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(endOfCheckoutPanelLayout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(thanksLabel))
                    .addGroup(endOfCheckoutPanelLayout.createSequentialGroup()
                        .addGap(267, 267, 267)
                        .addComponent(backToStoreButton)))
                .addContainerGap(210, Short.MAX_VALUE))
        );
        endOfCheckoutPanelLayout.setVerticalGroup(
            endOfCheckoutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(endOfCheckoutPanelLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(thanksLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83)
                .addComponent(backToStoreButton)
                .addContainerGap(441, Short.MAX_VALUE))
        );

        productPanel.add(endOfCheckoutPanel, "end");

        javax.swing.GroupLayout checkoutPanelLayout = new javax.swing.GroupLayout(checkoutPanel);
        checkoutPanel.setLayout(checkoutPanelLayout);
        checkoutPanelLayout.setHorizontalGroup(
            checkoutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, checkoutPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(checkoutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(progressPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(productPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(recommendedProductsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        checkoutPanelLayout.setVerticalGroup(
            checkoutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, checkoutPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(checkoutPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(recommendedProductsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(checkoutPanelLayout.createSequentialGroup()
                        .addComponent(progressPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(productPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 621, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(107, Short.MAX_VALUE))
        );

        featurePanel.add(checkoutPanel, "checkout");

        cartPanel.setBackground(new java.awt.Color(215, 173, 173));

        goToCashierBtn.setText("Gå till kassa");
        goToCashierBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToCashierBtnActionPerformed(evt);
            }
        });

        cartContentsPanel.setBackground(new java.awt.Color(215, 173, 173));
        cartContentsPanel.setLayout(new java.awt.GridLayout(1, 0));
        cartPanelScrollPane.setViewportView(cartContentsPanel);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("XstVaror");

        tpCartContentsNumber.setForeground(new java.awt.Color(51, 51, 51));
        tpCartContentsNumber.setText("varor");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Xkr");

        tpCartSumLabel.setForeground(new java.awt.Color(51, 51, 51));
        tpCartSumLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tpCartSumLabel.setText("kr");

        cartImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/shoppingCart.png"))); // NOI18N
        cartImage.setToolTipText("Kundvagn");

        javax.swing.GroupLayout cartPanelLayout = new javax.swing.GroupLayout(cartPanel);
        cartPanel.setLayout(cartPanelLayout);
        cartPanelLayout.setHorizontalGroup(
            cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cartPanelScrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cartPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(goToCashierBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(cartPanelLayout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(cartImage)
                        .addGap(45, 45, 45)
                        .addGroup(cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tpCartContentsNumber)
                            .addComponent(tpCartSumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(74, Short.MAX_VALUE))))
        );
        cartPanelLayout.setVerticalGroup(
            cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(cartPanelLayout.createSequentialGroup()
                        .addGroup(cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(tpCartContentsNumber))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tpCartSumLabel)))
                    .addComponent(cartImage, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cartPanelScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(goToCashierBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(featurePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 849, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(featurePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cartPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoPanelMouseClicked
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
    }//GEN-LAST:event_historyBtnActionPerformed

    private void searchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchFieldActionPerformed
/**
 *
 * Flytta detta till en egen metod, kalla från gpcon.
 */
    private void updCartBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updCartBtnActionPerformed
        
        cartContentsPanel.removeAll();

        List <ShoppingItem> cartContentList = gpCon.sc.getItems();

        for (int i = 0; i < gpCon.sc.getItems().size(); i++){
            cartContentsPanel.add(new JLabel(cartContentList.get(i).getProduct().getName()));
        }
        cartContentsPanel.add(new JLabel("Totalt: " + gpCon.sc.getTotal() + " kr"));
        repaint();
        revalidate();
    }//GEN-LAST:event_updCartBtnActionPerformed

    private void deliveryCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deliveryCheckBoxActionPerformed
        // Happens when the user checks/unchecks the delivery checkbox.

        if(deliveryCheckBox.isSelected()){
            addressPanel.setVisible(true);
        } else{
            addressPanel.setVisible(false);
        }

    }//GEN-LAST:event_deliveryCheckBoxActionPerformed

    private void saveButtonSaveButtonClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonSaveButtonClicked
        saveButton.setIcon(saveButtonClick);
        saveButton.setForeground(Color.WHITE);
    }//GEN-LAST:event_saveButtonSaveButtonClicked

    private void saveButtonSaveButtonHover(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonSaveButtonHover
        saveButton.setIcon(saveButtonHover);
    }//GEN-LAST:event_saveButtonSaveButtonHover

    private void saveButtonSaveButtonUnhover(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButtonSaveButtonUnhover
        saveButton.setIcon(saveButtonNormal);
        saveButton.setForeground(new Color(003400));
    }//GEN-LAST:event_saveButtonSaveButtonUnhover

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        gpCon.iMDH.shutDown();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void tpMyAccountLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tpMyAccountLabelMouseClicked
        cl.show(featurePanel, "myAccount");
    }//GEN-LAST:event_tpMyAccountLabelMouseClicked

    private void deliveryCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deliveryCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deliveryCheckBox1ActionPerformed

    private void nextStepToPersonalInfo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextStepToPersonalInfo
        //recommendedProductsPanel.setVisible(false);
        changeActivePurchaseStep("personalInfoDelivery", "shoppingCart");
        cl2.show(productPanel, "personalInfoDelivery");
        hideRecommendedProducts();
    }//GEN-LAST:event_nextStepToPersonalInfo

    private void nextStepToPayment(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextStepToPayment
        changeActivePurchaseStep("payment", "personalInfoDelivery");
        cl2.show(productPanel, "payment");
    }//GEN-LAST:event_nextStepToPayment

    private void goToCashierBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goToCashierBtnActionPerformed
        // Go to the checkout.
        
        resetActivePurchaseStep();
        //progressPanel.setVisible(true);
        //showProgressBar();
        //showRecommendedProducts();
        
        cl2.show(productPanel, "shoppingCart");
        cl.show(featurePanel, "checkout");
        
        progressPanel.setVisible(true);
        recommendedProductsPanel.setVisible(true);
    }//GEN-LAST:event_goToCashierBtnActionPerformed

    private void nextStepToAccept(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextStepToAccept
        // Go to the next step, to accept.
        changeActivePurchaseStep("accept", "payment");
        cl2.show(productPanel, "accept");
    }//GEN-LAST:event_nextStepToAccept

    private void postCodeTextField1PostCodeUpdate(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_postCodeTextField1PostCodeUpdate
        gpCon.iMDH.getInstance().getCustomer().setPostCode(postCodeTextField.getText());
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
        gpCon.iMDH.getCreditCard().setValidYear(validYearComboBox.getSelectedIndex());
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
        //hideProgressBar();
        //
        //progressPanel.hide();
        cl2.show(productPanel, "end");
        progressPanel.setVisible(false);
    }//GEN-LAST:event_acceptPurchase

    private void backToPersonalInfo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToPersonalInfo
        changeActivePurchaseStep("personalInfoDelivery", "payment");
        cl2.show(productPanel, "personalInfoDelivery");
    }//GEN-LAST:event_backToPersonalInfo

    private void backToPayment(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToPayment
        changeActivePurchaseStep("payment", "accept");
        cl2.show(productPanel, "payment");
    }//GEN-LAST:event_backToPayment

    private void backToShoppingCart(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToShoppingCart
        recommendedProductsPanel.setVisible(true);
        changeActivePurchaseStep("shoppingCart", "personalInfoDelivery");
        cl2.show(productPanel, "shoppingCart");
    }//GEN-LAST:event_backToShoppingCart

    private void backToStore(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToStore
        cl.show(featurePanel, "cardStart");
        cl2.show(productPanel, "shoppingCart");
    }//GEN-LAST:event_backToStore

    private void lpFavoritesLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lpFavoritesLabelMouseClicked
        cl.show(featurePanel, "cardFavorites");
    }//GEN-LAST:event_lpFavoritesLabelMouseClicked

    private void lpDealsLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lpDealsLabelMouseClicked
        cl.show(featurePanel, "cardDeals");
    }//GEN-LAST:event_lpDealsLabelMouseClicked

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
    private javax.swing.JLabel acceptVerificationCodeLabel;
    private javax.swing.JLabel accountImage;
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
    private javax.swing.JTextField cardHolderTextField1;
    private javax.swing.JLabel cardNumberLabel;
    private javax.swing.JLabel cardNumberLabel1;
    private javax.swing.JTextField cardNumberTextField;
    private javax.swing.JTextField cardNumberTextField1;
    private javax.swing.JComboBox cardTypeComboBox;
    private javax.swing.JComboBox cardTypeComboBox1;
    private javax.swing.JLabel cardTypeLabel;
    private javax.swing.JLabel cardTypeLabel1;
    private javax.swing.JPanel cartContentsPanel;
    private javax.swing.JLabel cartImage;
    private javax.swing.JPanel cartPanel;
    private javax.swing.JScrollPane cartPanelScrollPane;
    private javax.swing.JTextField cashierAddressTF;
    private javax.swing.JTextField cashierEmailTF;
    private javax.swing.JTextField cashierFirstNameTF;
    private javax.swing.JTextField cashierLastNameTF;
    private javax.swing.JTextField cashierOtherAddressTF;
    private javax.swing.JTextField cashierOtherPostCodeTF;
    private javax.swing.JTextField cashierPhoneNumberTF;
    private javax.swing.JTextField cashierPostCodeTF;
    private javax.swing.JLabel categoryFeatureLabel;
    private javax.swing.JPanel categoryFeaturePanel;
    private javax.swing.JPanel categoryPanel;
    private javax.swing.JPanel checkPaymentPanel;
    private javax.swing.JPanel checkPersonalInfoPanel;
    private javax.swing.JLabel checkoutAcceptLabel;
    private javax.swing.JLabel checkoutDeliveryLabel;
    private javax.swing.JPanel checkoutPanel;
    private javax.swing.JLabel checkoutPaymentLabel;
    private javax.swing.JLabel checkoutShoppingCartLabel;
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
    private javax.swing.JLabel fpHistoryLabel;
    private javax.swing.JLabel fpMyFavoritesLabel;
    private javax.swing.JLabel fpMyShoppingBagsLabel;
    private javax.swing.JLabel fpRecipesLabel;
    private javax.swing.JButton goToCashierBtn;
    private javax.swing.JButton historyBtn;
    private javax.swing.JPanel historyViewPanel;
    private javax.swing.JLabel iMatLabel;
    private javax.swing.JPanel itemShower;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
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
    private javax.swing.JPanel myFavoritesViewPanel;
    private javax.swing.JButton myShoppingBagsBtn;
    private javax.swing.JPanel myShoppingBagsViewPanel;
    private javax.swing.JButton nextStepButtonToAccept;
    private javax.swing.JButton nextStepButtonToPayment;
    private javax.swing.JButton nextStepButtonToPersonalInfo;
    private javax.swing.JSeparator optCatSeparator;
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
    private javax.swing.JPanel productPanel;
    private javax.swing.JScrollPane productScrollPane;
    private javax.swing.JPanel progressPanel;
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
    private javax.swing.JPanel startViewPanel;
    private javax.swing.JLabel thanksLabel;
    private javax.swing.JLabel titleNameLabel;
    private javax.swing.JPanel topPanel;
    private javax.swing.JLabel totalPriceLabel;
    private javax.swing.JLabel tpCartContentsNumber;
    private javax.swing.JLabel tpCartSumLabel;
    private javax.swing.JLabel tpMyAccountLabel;
    private javax.swing.JButton updCartBtn;
    private javax.swing.JComboBox validMonthComboBox;
    private javax.swing.JComboBox validMonthComboBox1;
    private javax.swing.JComboBox validYearComboBox;
    private javax.swing.JComboBox validYearComboBox1;
    private javax.swing.JLabel verificationCodeLabel;
    private javax.swing.JLabel verificationCodeLabel1;
    private javax.swing.JTextField verificationCodeTextField;
    private javax.swing.JTextField verificationCodeTextField1;
    // End of variables declaration//GEN-END:variables
}
