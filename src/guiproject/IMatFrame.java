/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiproject;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import se.chalmers.ait.dat215.project.ProductCategory;
import se.chalmers.ait.dat215.project.ShoppingItem;

/**
 *
 * @author Johan
 */


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
    GUIProject gpCon = new GUIProject();
    
    private CardLayout cl;
    private CardLayout cl2;
    
    private final AddressPanel addressPanel1 = new AddressPanel();
    
    //var används dessa?
    private final ImageIcon saveButtonNormal = new ImageIcon(getClass().getResource("/greenButton.png"));
    private final ImageIcon saveButtonHover = new ImageIcon(getClass().getResource("/greenButtonHover.png"));
    private final ImageIcon saveButtonClick = new ImageIcon(getClass().getResource("/greenButtonClick.png"));
    
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
    
    public void updateCartPanel(){
                                                   
        cartContentsPanel.removeAll();
        
        List <ShoppingItem> cartContentList = gpCon.sc.getItems();
        
        for (int i = 0; i < gpCon.sc.getItems().size(); i++){
            cartContentsPanel.add(new JLabel(cartContentList.get(i).getProduct().getName() + 
                   " " + cartContentList.get(i).getProduct().getPrice() + " " +
                    cartContentList.get(i).getProduct().getUnit() + " "
                    + cartContentList.get(i).getAmount() + " " 
            + cartContentList.get(i).getProduct().getUnitSuffix()));
        }
        
        cartContentsPanel.add(new JLabel("Totalt: " + gpCon.sc.getTotal() + " kr"));
//        for (int i = 0; i < gpCon.cartContents.size(); i++){
//        cartContentsPanel.add(new JLabel(gpCon.cartContents.get(i).toString()));
//            System.out.println(gpCon.cartContents.get(i).toString());
//        }
        repaint();
        revalidate();
                               
    }
    private void displayGroceries(){
                
        GridLayout layout = new GridLayout(gpCon.products.size(),1);
                              
        itemShower.setLayout(layout);
        itemShower.removeAll();
        for (int i = 0; i < gpCon.products.size(); i++) {
            itemShower.add(gpCon.productCards.get(i));
        }

        cl.show(featurePanel, "searchResultPanel");
        revalidate();
        repaint();
    }
    
    /**
     * Creates new form iMatFrame
     */

    public IMatFrame() {
        initComponents();
        
        revalidate();

        cl = (CardLayout)featurePanel.getLayout();
        cl2 = (CardLayout)productPanel.getLayout();
        
        deliveryPanel.add(addressPanel1);
        
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
            JLabel subCategoryFruitLabel = new JLabel("Frukter");
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
    }
    

    private void placeCategories(){
        

        
        
        for (int i = 0; i<allCategoryArrayList.size(); i++){
                categoryPanel.add(allCategoryArrayList.get(i));
                allCategoryArrayList.get(i).setHorizontalAlignment(SwingConstants.RIGHT);
                allCategoryArrayList.get(i).setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));


        }
    }
    
    public void actionPerformed(ActionEvent e) {
        
    }
    
    /**
     * Ännu ej särskilt funktionell kod för att utöka kategorilistan med en underkategori
     * när en kategorilabel klickas på. Behöver få tillgång till categoryArrayList.get(i).
     * TODO: dölj/ta bort andra kategoriers underkategorier när man går in på en ny.
     * Kanske underlättar om man lägger underkategorier sist i categoryListArray men på specifik plats i panelen?
     */
    private void categoryLabelMouseClicked(java.awt.event.MouseEvent evt) {   
        

        JLabel tempLabel1 = (JLabel)evt.getSource();
 
        categoryPanel.removeAll();
        allCategoryArrayList.clear();
        for (int i = 0; i<headCategoryArrayList.size(); i++){
            allCategoryArrayList.add(headCategoryArrayList.get(i));
        }
        switch(tempLabel1.getText()){
            case "Bröd och torrvaror": 
                for (int i = 0; i<breadCategoryArrayList.size(); i++){
                    allCategoryArrayList.add(1+i, breadCategoryArrayList.get(i));
                                                
                }
                
                break;
            case "Drycker": 
                for (int i = 0; i<drinkCategoryArrayList.size(); i++){
                    
                    allCategoryArrayList.add(2+i, drinkCategoryArrayList.get(i));
                }
                
                break;
//            case "Fisk": 
//                for (int i = 0; i<fishCategoryArrayList.size(); i++){
//                    
//                    allCategoryArrayList.add(3+i, fishCategoryArrayList.get(i));
//                }
//                
//                break;
            case "Frukt och grönt": 
                for (int i = 0; i<fruitVegsCategoryArrayList.size(); i++){
                    
                    allCategoryArrayList.add(4+i, fruitVegsCategoryArrayList.get(i));
                }
                
                break;
            case "Kryddor": 
                for (int i = 0; i<spiceCategoryArrayList.size(); i++){
                    
                    allCategoryArrayList.add(5+i, spiceCategoryArrayList.get(i));
                }
                
                break;
//            case "Kött": 
//                for (int i = 0; i<meatCategoryArrayList.size(); i++){
//                    
//                    allCategoryArrayList.add(6+i, meatCategoryArrayList.get(i));
//                }
//                
//                break;
//            case "Mejeriprodukter": 
//                for (int i = 0; i<dairiesCategoryArrayList.size(); i++){
//                    
//                    allCategoryArrayList.add(7+i, dairiesCategoryArrayList.get(i));
//                }
//                
//                break;
//            case "Sötsaker": 
//                for (int i = 0; i<sweetsCategoryArrayList.size(); i++){
//                    
//                    allCategoryArrayList.add(8+i, sweetsCategoryArrayList.get(i));
//                }
//                
//                break;
               
            case "Baljväxter":
                gpCon.listCatProds("POD", this);
                displayGroceries();
                break;
            case "Bröd":
                gpCon.listCatProds("BREAD", this);
                displayGroceries();
                break;
            case "Bär":
                gpCon.listCatProds("BERRY", this);
                displayGroceries();
                break;
            case "Citrusfrukter":
                gpCon.listCatProds("CITRUS_FRUIT", this);
                displayGroceries();
                break;
                
            case "Varma drycker":
                gpCon.listCatProds("HOT_DRINKS", this);
                displayGroceries();
                break;
            case "Kalla drycker":
                gpCon.listCatProds("COLD_DRINKS", this);
                displayGroceries();
                break;
                
            case "Exotiska frukter":
                gpCon.listCatProds("EXOTIC_FRUIT", this);
                displayGroceries();
                break;
            case "Fisk":
                gpCon.listCatProds("FISH", this);
                displayGroceries();
                break;
                
            case "Grönsaksfrukter":
                gpCon.listCatProds("VEGETABLE_FRUIT", this);
                displayGroceries();
                break;
            case "Kål":
                gpCon.listCatProds("CABBAGE", this);
                displayGroceries();
                break;
            case "Kött":
                gpCon.listCatProds("MEAT", this);
                displayGroceries();
                break;
            case "Mejeriprodukter":
                gpCon.listCatProds("DAIRIES", this);
                displayGroceries();
                break;
            case "Meloner":
                gpCon.listCatProds("MELONS", this);
                displayGroceries();
                break;
           
            case "Mjöl, socker och salt":
                gpCon.listCatProds("FLOUR_SUGAR_SALT", this);
                displayGroceries();
                break;
            case "Nötter och frön":
                gpCon.listCatProds("NUTS_AND_SEEDS", this);
                displayGroceries();
                break;
            case "Pasta":
                gpCon.listCatProds("PASTA", this);
                displayGroceries();
                break;
            case "Potatis och ris":
                gpCon.listCatProds("POTATO_RICE", this);
                displayGroceries();
                break;
            case "Rotfrukter":
                gpCon.listCatProds("ROOT_VEGETABLE", this);
                displayGroceries();
                break;
             
            case "Frukt":
                gpCon.listCatProds("FRUIT", this);
                displayGroceries();
                break;
            case "Sötsaker":
                gpCon.listCatProds("SWEET", this);
                displayGroceries();
                break;
            case "Örter":
                gpCon.listCatProds("HERB", this);
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
        searchButton = new javax.swing.JButton();
        categoryPanel = new javax.swing.JPanel();
        topPanel = new javax.swing.JPanel();
        cartImage = new javax.swing.JLabel();
        tpMyAccountLabel = new javax.swing.JLabel();
        tpChangeUserLabel = new javax.swing.JLabel();
        accountImage = new javax.swing.JLabel();
        tpCartContentsNumber = new javax.swing.JLabel();
        tpCartSumLabel = new javax.swing.JLabel();
        tpGoToCashierLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        myShoppingBagsBtn = new javax.swing.JButton();
        myFavoritesBtn = new javax.swing.JButton();
        recipesBtn = new javax.swing.JButton();
        dealsBtn = new javax.swing.JButton();
        historyBtn = new javax.swing.JButton();
        updCartBtn = new javax.swing.JButton();
        featurePanel = new javax.swing.JPanel();
        startViewPanel = new javax.swing.JPanel();
        recentlyBoughtLabel = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        popularLabel = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        dealsLabel = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
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
        category1MenuLabel = new javax.swing.JLabel();
        category2MenuLabel = new javax.swing.JLabel();
        category3MenuLabel = new javax.swing.JLabel();
        category4MenuLabel = new javax.swing.JLabel();
        category5MenuLabel = new javax.swing.JLabel();
        category6MenuLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
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
        myAccountTopPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        titleNameLabel = new javax.swing.JLabel();
        cashierPanel = new javax.swing.JPanel();
        progressPanel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
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
        nextStepButtonToAccept1 = new javax.swing.JButton();
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
        logoPanel = new javax.swing.JPanel();
        iMatLabel = new javax.swing.JLabel();
        cartPanel = new javax.swing.JPanel();
        cartHeadlineLabel = new javax.swing.JLabel();
        goToCashierBtn = new javax.swing.JButton();
        cartPanelScrollPane = new javax.swing.JScrollPane();
        cartContentsPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        leftPanel.setBackground(new java.awt.Color(204, 0, 0));

        leftUpperPanel.setBackground(new java.awt.Color(204, 0, 0));
        leftUpperPanel.setLayout(new java.awt.BorderLayout());

        searchField.setText("Skriv här...");
        searchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchFieldActionPerformed(evt);
            }
        });
        leftUpperPanel.add(searchField, java.awt.BorderLayout.CENTER);

        searchButton.setText("sök!");
        searchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchButtonMouseClicked(evt);
            }
        });
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });
        leftUpperPanel.add(searchButton, java.awt.BorderLayout.PAGE_END);

        categoryPanel.setBackground(new java.awt.Color(204, 0, 0));
        categoryPanel.setLayout(new java.awt.GridLayout(30, 1));

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(categoryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
            .addComponent(leftUpperPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addComponent(leftUpperPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(categoryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                .addContainerGap())
        );

        topPanel.setBackground(new java.awt.Color(204, 0, 0));

        cartImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guiKundvagn.png"))); // NOI18N

        tpMyAccountLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tpMyAccountLabel.setText("Mitt konto");
        tpMyAccountLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tpMyAccountLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tpMyAccountLabelMouseClicked(evt);
            }
        });

        tpChangeUserLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tpChangeUserLabel.setText("Byt användare");
        tpChangeUserLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        accountImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guiProfile.png"))); // NOI18N

        tpCartContentsNumber.setText("Varor");

        tpCartSumLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tpCartSumLabel.setText("kr");

        tpGoToCashierLabel.setText("Gå till kassan");
        tpGoToCashierLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("XstVaror");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Xkr");

        myShoppingBagsBtn.setText("Mina matkassar");
        myShoppingBagsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myShoppingBagsBtnActionPerformed(evt);
            }
        });

        myFavoritesBtn.setText("Favoriter");
        myFavoritesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myFavoritesBtnActionPerformed(evt);
            }
        });

        recipesBtn.setText("Recept");
        recipesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recipesBtnActionPerformed(evt);
            }
        });

        dealsBtn.setText("Erbjudanden");
        dealsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dealsBtnActionPerformed(evt);
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

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addComponent(myShoppingBagsBtn)
                .addGap(18, 18, 18)
                .addComponent(myFavoritesBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addComponent(recipesBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dealsBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(historyBtn)
                        .addGap(161, 161, 161))
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addComponent(updCartBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(cartImage)
                .addGap(28, 28, 28)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tpGoToCashierLabel)
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tpCartSumLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tpCartContentsNumber))))
                .addGap(49, 49, 49)
                .addComponent(accountImage)
                .addGap(18, 18, 18)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tpMyAccountLabel)
                    .addComponent(tpChangeUserLabel))
                .addGap(32, 32, 32))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(updCartBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(myShoppingBagsBtn)
                            .addComponent(myFavoritesBtn)
                            .addComponent(recipesBtn)
                            .addComponent(dealsBtn)
                            .addComponent(historyBtn)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(accountImage)
                                .addGroup(topPanelLayout.createSequentialGroup()
                                    .addComponent(tpMyAccountLabel)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(tpChangeUserLabel)
                                    .addGap(18, 18, 18)))
                            .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(topPanelLayout.createSequentialGroup()
                                    .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tpCartContentsNumber)
                                        .addComponent(jLabel2))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tpCartSumLabel)
                                        .addComponent(jLabel3))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(tpGoToCashierLabel))
                                .addComponent(cartImage)))))
                .addGap(49, 49, 49))
        );

        featurePanel.setBackground(new java.awt.Color(255, 51, 255));
        featurePanel.setLayout(new java.awt.CardLayout());

        startViewPanel.setBackground(new java.awt.Color(255, 255, 204));

        recentlyBoughtLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        recentlyBoughtLabel.setText("Mina senaste inköp");

        popularLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        popularLabel.setText("Populärt");

        dealsLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        dealsLabel.setText("Erbjudanden");

        javax.swing.GroupLayout startViewPanelLayout = new javax.swing.GroupLayout(startViewPanel);
        startViewPanel.setLayout(startViewPanelLayout);
        startViewPanelLayout.setHorizontalGroup(
            startViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(startViewPanelLayout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addGroup(startViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dealsLabel)
                    .addComponent(recentlyBoughtLabel)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(popularLabel))
                .addContainerGap(575, Short.MAX_VALUE))
        );
        startViewPanelLayout.setVerticalGroup(
            startViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(startViewPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(recentlyBoughtLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(104, 104, 104)
                .addComponent(popularLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(214, 214, 214)
                .addComponent(dealsLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(200, Short.MAX_VALUE))
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
                .addGap(0, 766, Short.MAX_VALUE))
        );
        myShoppingBagsViewPanelLayout.setVerticalGroup(
            myShoppingBagsViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myShoppingBagsViewPanelLayout.createSequentialGroup()
                .addComponent(fpMyShoppingBagsLabel)
                .addGap(0, 639, Short.MAX_VALUE))
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
                .addGap(0, 786, Short.MAX_VALUE))
        );
        myFavoritesViewPanelLayout.setVerticalGroup(
            myFavoritesViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myFavoritesViewPanelLayout.createSequentialGroup()
                .addComponent(fpMyFavoritesLabel)
                .addGap(0, 639, Short.MAX_VALUE))
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
                .addGap(0, 861, Short.MAX_VALUE))
        );
        recipesViewPanelLayout.setVerticalGroup(
            recipesViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recipesViewPanelLayout.createSequentialGroup()
                .addComponent(fpRecipesLabel)
                .addGap(0, 639, Short.MAX_VALUE))
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
                .addGap(0, 801, Short.MAX_VALUE))
        );
        dealsViewPanelLayout.setVerticalGroup(
            dealsViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dealsViewPanelLayout.createSequentialGroup()
                .addComponent(fpDealsLabel)
                .addGap(0, 639, Short.MAX_VALUE))
        );

        featurePanel.add(dealsViewPanel, "cardDeals");

        fpHistoryLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        fpHistoryLabel.setText("Historik");

        category1MenuLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        category1MenuLabel.setText("Kategori 1");
        category1MenuLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        category1MenuLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                category1MenuLabelMouseClicked(evt);
            }
        });

        category2MenuLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        category2MenuLabel.setText("Kategori 2");
        category2MenuLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        category3MenuLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        category3MenuLabel.setText("Kategori 3");
        category3MenuLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        category4MenuLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        category4MenuLabel.setText("Kategori 4");
        category4MenuLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        category5MenuLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        category5MenuLabel.setText("Kategori 5");
        category5MenuLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        category6MenuLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        category6MenuLabel.setText("Kategori 6");
        category6MenuLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("OBS dessa kategorilabels används ej, ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("men får ligga kvar ett tag till");

        javax.swing.GroupLayout historyViewPanelLayout = new javax.swing.GroupLayout(historyViewPanel);
        historyViewPanel.setLayout(historyViewPanelLayout);
        historyViewPanelLayout.setHorizontalGroup(
            historyViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(historyViewPanelLayout.createSequentialGroup()
                .addComponent(fpHistoryLabel)
                .addGap(0, 855, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, historyViewPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(408, 408, 408))
            .addGroup(historyViewPanelLayout.createSequentialGroup()
                .addGap(505, 505, 505)
                .addGroup(historyViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(category1MenuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(category2MenuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(category3MenuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(category4MenuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(category5MenuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(category6MenuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        historyViewPanelLayout.setVerticalGroup(
            historyViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(historyViewPanelLayout.createSequentialGroup()
                .addComponent(fpHistoryLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 298, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(category1MenuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(category2MenuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(category3MenuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(category4MenuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(category5MenuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(category6MenuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(128, 128, 128))
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
                .addContainerGap(858, Short.MAX_VALUE))
        );
        categoryFeaturePanelLayout.setVerticalGroup(
            categoryFeaturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(categoryFeaturePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(categoryFeatureLabel)
                .addContainerGap(643, Short.MAX_VALUE))
        );

        featurePanel.add(categoryFeaturePanel, "categoryFeaturePanel");

        searchResultPanel.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setBackground(new java.awt.Color(244, 221, 173));
        jPanel1.setLayout(new java.awt.GridBagLayout());

        itemShower.setLayout(new java.awt.GridLayout(1, 2));
        jPanel1.add(itemShower, new java.awt.GridBagConstraints());

        jScrollPane1.setViewportView(jPanel1);

        searchResultPanel.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        featurePanel.add(searchResultPanel, "searchResultPanel");

        myAccountPanel.setBackground(new java.awt.Color(255, 255, 255));
        myAccountPanel.setPreferredSize(new java.awt.Dimension(1121, 525));

        myAccountContentPanel.setBackground(new java.awt.Color(255, 255, 255));

        personalInfoPanel.setBackground(new java.awt.Color(255, 255, 255));

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

        paymentInfoPanel.setBackground(new java.awt.Color(255, 255, 255));

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

        deliveryPanel.setBackground(new java.awt.Color(255, 255, 255));

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

        deliverToMyAdressPanel.setBackground(new java.awt.Color(255, 255, 255));

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
            public void mouseExited(java.awt.event.MouseEvent evt) {
                saveButtonSaveButtonUnhover(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                saveButtonSaveButtonHover(evt);
            }
        });
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        addressPanel.setBackground(new java.awt.Color(255, 255, 255));

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
                .addContainerGap(196, Short.MAX_VALUE))
        );
        myAccountContentPanelLayout.setVerticalGroup(
            myAccountContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myAccountContentPanelLayout.createSequentialGroup()
                .addGroup(myAccountContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(personalInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(paymentInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        myAccountTopPanel.setBackground(new java.awt.Color(255, 255, 255));
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

        progressPanel.setBackground(new java.awt.Color(255, 153, 255));

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/greenButton.png"))); // NOI18N
        jLabel5.setText("Kundvagn");
        jLabel5.setFocusCycleRoot(true);
        jLabel5.setIconTextGap(-90);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/greenButton.png"))); // NOI18N
        jLabel7.setText("Leverans");
        jLabel7.setFocusCycleRoot(true);
        jLabel7.setFocusTraversalPolicyProvider(true);
        jLabel7.setIconTextGap(-85);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/greenButton.png"))); // NOI18N
        jLabel8.setText("Betalning");
        jLabel8.setFocusCycleRoot(true);
        jLabel8.setIconTextGap(-85);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/greenButton.png"))); // NOI18N
        jLabel9.setText("Slutför köp");
        jLabel9.setFocusCycleRoot(true);
        jLabel9.setIconTextGap(-90);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 153, 153));
        jLabel10.setText(">");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(153, 153, 153));
        jLabel11.setText(">");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 153, 153));
        jLabel13.setText(">");

        javax.swing.GroupLayout progressPanelLayout = new javax.swing.GroupLayout(progressPanel);
        progressPanel.setLayout(progressPanelLayout);
        progressPanelLayout.setHorizontalGroup(
            progressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(progressPanelLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        progressPanelLayout.setVerticalGroup(
            progressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, progressPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(progressPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13))
                .addContainerGap())
        );

        recommendedProductsPanel.setBackground(new java.awt.Color(204, 255, 153));

        recommendedProductsLabel.setFont(new java.awt.Font("Gautami", 0, 18)); // NOI18N
        recommendedProductsLabel.setForeground(new java.awt.Color(102, 102, 102));
        recommendedProductsLabel.setText("Har du glömt...?");

        recommendedProductsScrollPane.setBorder(null);

        javax.swing.GroupLayout recommendedProductsPanelLayout = new javax.swing.GroupLayout(recommendedProductsPanel);
        recommendedProductsPanel.setLayout(recommendedProductsPanelLayout);
        recommendedProductsPanelLayout.setHorizontalGroup(
            recommendedProductsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recommendedProductsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(recommendedProductsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(recommendedProductsScrollPane)
                    .addComponent(recommendedProductsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                .addContainerGap())
        );
        recommendedProductsPanelLayout.setVerticalGroup(
            recommendedProductsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recommendedProductsPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(recommendedProductsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(recommendedProductsScrollPane)
                .addContainerGap())
        );

        productPanel.setBackground(new java.awt.Color(204, 204, 255));
        productPanel.setLayout(new java.awt.CardLayout());

        shoppingCartPanel.setBackground(new java.awt.Color(204, 204, 255));

        nextStepButtonToPersonalInfo.setText("Gå vidare");
        nextStepButtonToPersonalInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextStepToPersonalInfo(evt);
            }
        });

        totalPriceLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        totalPriceLabel.setText("Totalt");

        priceLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        priceLabel.setText("pris");

        krLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
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
                .addContainerGap(66, Short.MAX_VALUE))
        );
        shoppingCartPanelLayout.setVerticalGroup(
            shoppingCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(shoppingCartPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(productScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 489, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(shoppingCartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalPriceLabel)
                    .addComponent(priceLabel)
                    .addComponent(krLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addComponent(nextStepButtonToPersonalInfo)
                .addContainerGap())
        );

        productPanel.add(shoppingCartPanel, "shoppingCart");

        checkPersonalInfoPanel.setBackground(new java.awt.Color(204, 204, 255));

        backButtonToShoppingCart.setText("Tillbaka");
        backButtonToShoppingCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptBackToShoppingCart(evt);
            }
        });

        nextStepButtonToPayment.setText("Gå vidare");
        nextStepButtonToPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NextStepToPayment(evt);
            }
        });

        personalInfoPanel1.setBackground(new java.awt.Color(255, 255, 255));

        personalInfoLabel2.setFont(new java.awt.Font("Gautami", 0, 18)); // NOI18N
        personalInfoLabel2.setForeground(new java.awt.Color(51, 51, 51));
        personalInfoLabel2.setText("Personlig information");

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(6, 6, 6)
                .addComponent(personalInfoLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        saveButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                saveButton1SaveButtonClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                saveButton1SaveButtonHover(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                saveButton1SaveButtonUnhover(evt);
            }
        });

        javax.swing.GroupLayout checkPersonalInfoPanelLayout = new javax.swing.GroupLayout(checkPersonalInfoPanel);
        checkPersonalInfoPanel.setLayout(checkPersonalInfoPanelLayout);
        checkPersonalInfoPanelLayout.setHorizontalGroup(
            checkPersonalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkPersonalInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(checkPersonalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(checkPersonalInfoPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(backButtonToShoppingCart)
                        .addGap(18, 18, 18)
                        .addComponent(nextStepButtonToPayment))
                    .addGroup(checkPersonalInfoPanelLayout.createSequentialGroup()
                        .addComponent(personalInfoPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deliveryPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, checkPersonalInfoPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveButton1)
                .addGap(124, 124, 124))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 125, Short.MAX_VALUE)
                .addGroup(checkPersonalInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButtonToShoppingCart)
                    .addComponent(nextStepButtonToPayment))
                .addContainerGap())
        );

        productPanel.add(checkPersonalInfoPanel, "personalInfoDelivery");

        checkPaymentPanel.setBackground(new java.awt.Color(204, 204, 255));

        backButtonToPersonalInfo.setText("Tillbaka");
        backButtonToPersonalInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonToPersonalInfo(evt);
            }
        });

        nextStepButtonToAccept.setText("Gå vidare");
        nextStepButtonToAccept.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextStepButtonToAccept(evt);
            }
        });

        paymentInfoPanel1.setBackground(new java.awt.Color(255, 255, 255));

        paymentInfoLabel1.setFont(new java.awt.Font("Gautami", 0, 18)); // NOI18N
        paymentInfoLabel1.setForeground(new java.awt.Color(51, 51, 51));
        paymentInfoLabel1.setText("Betalning");

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
            .addGroup(checkPaymentPanelLayout.createSequentialGroup()
                .addContainerGap(534, Short.MAX_VALUE)
                .addComponent(backButtonToPersonalInfo)
                .addGap(18, 18, 18)
                .addComponent(nextStepButtonToAccept)
                .addContainerGap())
            .addGroup(checkPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(checkPaymentPanelLayout.createSequentialGroup()
                    .addGap(167, 167, 167)
                    .addComponent(paymentInfoPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(168, Short.MAX_VALUE)))
        );
        checkPaymentPanelLayout.setVerticalGroup(
            checkPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkPaymentPanelLayout.createSequentialGroup()
                .addContainerGap(576, Short.MAX_VALUE)
                .addGroup(checkPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButtonToPersonalInfo)
                    .addComponent(nextStepButtonToAccept))
                .addContainerGap())
            .addGroup(checkPaymentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(checkPaymentPanelLayout.createSequentialGroup()
                    .addGap(14, 14, 14)
                    .addComponent(paymentInfoPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(364, Short.MAX_VALUE)))
        );

        productPanel.add(checkPaymentPanel, "payment");

        acceptPanel.setBackground(new java.awt.Color(204, 204, 255));

        backButtonToPayment.setText("Tillbaka");
        backButtonToPayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cashierBackToPayment(evt);
            }
        });

        nextStepButtonToAccept1.setText("Slutför köp");
        nextStepButtonToAccept1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextStepButtonToAccept1(evt);
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

        acceptDeliveryLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        acceptDeliveryLabel.setForeground(new java.awt.Color(51, 51, 51));
        acceptDeliveryLabel.setText("Leverans");

        acceptShoppingCartLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        acceptShoppingCartLabel.setForeground(new java.awt.Color(51, 51, 51));
        acceptShoppingCartLabel.setText("Kundvagn");

        acceptSeparator1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redSeparator.png"))); // NOI18N

        acceptSeparator3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redSeparator.png"))); // NOI18N

        acceptPaymentLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        acceptPaymentLabel.setForeground(new java.awt.Color(51, 51, 51));
        acceptPaymentLabel.setText("Betalning");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(backButtonToPayment)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nextStepButtonToAccept1)
                .addGap(31, 31, 31))
        );
        acceptPanelLayout.setVerticalGroup(
            acceptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(acceptPanelLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(acceptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(acceptPanelLayout.createSequentialGroup()
                        .addGroup(acceptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(acceptShoppingCartLabel)
                            .addComponent(acceptDeliveryLabel))
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
                        .addGap(57, 57, 57)
                        .addComponent(acceptPaymentLabel)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 141, Short.MAX_VALUE)
                .addGroup(acceptPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButtonToPayment)
                    .addComponent(nextStepButtonToAccept1))
                .addContainerGap())
        );

        productPanel.add(acceptPanel, "accept");

        javax.swing.GroupLayout cashierPanelLayout = new javax.swing.GroupLayout(cashierPanel);
        cashierPanel.setLayout(cashierPanelLayout);
        cashierPanelLayout.setHorizontalGroup(
            cashierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cashierPanelLayout.createSequentialGroup()
                .addGroup(cashierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(progressPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(productPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(recommendedProductsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        cashierPanelLayout.setVerticalGroup(
            cashierPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cashierPanelLayout.createSequentialGroup()
                .addComponent(progressPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(productPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(recommendedProductsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        featurePanel.add(cashierPanel, "cashier");

        logoPanel.setBackground(new java.awt.Color(204, 0, 0));
        logoPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoPanelMouseClicked(evt);
            }
        });

        iMatLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        iMatLabel.setText("iMat");
        iMatLabel.setToolTipText("Gå till startsidan");
        iMatLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout logoPanelLayout = new javax.swing.GroupLayout(logoPanel);
        logoPanel.setLayout(logoPanelLayout);
        logoPanelLayout.setHorizontalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logoPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(iMatLabel)
                .addContainerGap())
        );
        logoPanelLayout.setVerticalGroup(
            logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logoPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(iMatLabel)
                .addContainerGap())
        );

        cartPanel.setBackground(new java.awt.Color(204, 100, 0));

        cartHeadlineLabel.setText("Kundvagn");

        goToCashierBtn.setText("Gå till kassa");
        goToCashierBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToCashierBtnActionPerformed(evt);
            }
        });

        cartContentsPanel.setBackground(new java.awt.Color(204, 100, 0));
        cartContentsPanel.setLayout(new java.awt.GridLayout(20, 1));
        cartPanelScrollPane.setViewportView(cartContentsPanel);

        javax.swing.GroupLayout cartPanelLayout = new javax.swing.GroupLayout(cartPanel);
        cartPanel.setLayout(cartPanelLayout);
        cartPanelLayout.setHorizontalGroup(
            cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(goToCashierBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cartPanelScrollPane)
                    .addComponent(cartHeadlineLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        cartPanelLayout.setVerticalGroup(
            cartPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cartPanelLayout.createSequentialGroup()
                .addComponent(cartHeadlineLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cartPanelScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(goToCashierBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(featurePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 934, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(featurePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(leftPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cartPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoPanelMouseClicked
        cl.show(featurePanel, "cardStart");
    }//GEN-LAST:event_logoPanelMouseClicked

    private void category1MenuLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_category1MenuLabelMouseClicked
        revalidate();
    }//GEN-LAST:event_category1MenuLabelMouseClicked

    private void myShoppingBagsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myShoppingBagsBtnActionPerformed
        cl.show(featurePanel, "cardBags");
    }//GEN-LAST:event_myShoppingBagsBtnActionPerformed

    private void myFavoritesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myFavoritesBtnActionPerformed
        cl.show(featurePanel, "cardFavorites");
    }//GEN-LAST:event_myFavoritesBtnActionPerformed

    private void recipesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recipesBtnActionPerformed
        cl.show(featurePanel, "cardRecipes");
    }//GEN-LAST:event_recipesBtnActionPerformed

    private void dealsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dealsBtnActionPerformed
        cl.show(featurePanel, "cardDeals");
    }//GEN-LAST:event_dealsBtnActionPerformed

    private void historyBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_historyBtnActionPerformed
        cl.show(featurePanel, "cardHistory");
    }//GEN-LAST:event_historyBtnActionPerformed

    private void searchButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchButtonMouseClicked
        cl.show(featurePanel, "searchResultPanel");
    }//GEN-LAST:event_searchButtonMouseClicked

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        gpCon.doSearch(searchField.getText(), this);
        displayGroceries();
        repaint();

    }//GEN-LAST:event_searchButtonActionPerformed

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
//        for (int i = 0; i < gpCon.cartContents.size(); i++){
//        cartContentsPanel.add(new JLabel(gpCon.cartContents.get(i).toString()));
//            System.out.println(gpCon.cartContents.get(i).toString());
//        }
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

    private void saveButton1SaveButtonClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButton1SaveButtonClicked
        saveButton1.setIcon(saveButtonClick);
        saveButton1.setForeground(Color.WHITE);
    }//GEN-LAST:event_saveButton1SaveButtonClicked

    private void saveButton1SaveButtonUnhover(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButton1SaveButtonUnhover
        saveButton1.setIcon(saveButtonNormal);
        saveButton1.setForeground(new Color(003400));
    }//GEN-LAST:event_saveButton1SaveButtonUnhover

    private void saveButton1SaveButtonHover(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_saveButton1SaveButtonHover
        saveButton1.setIcon(saveButtonHover);
    }//GEN-LAST:event_saveButton1SaveButtonHover

    private void NextStepToPersonalInfo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextStepToPersonalInfo
        cl2.show(productPanel, "personalInfoDelivery");
    }//GEN-LAST:event_NextStepToPersonalInfo

    private void NextStepToPayment(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NextStepToPayment
        cl2.show(productPanel, "payment");
    }//GEN-LAST:event_NextStepToPayment

    private void goToCashierBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goToCashierBtnActionPerformed
        // Go to the cashier.
        cl.show(featurePanel, "cashier");
    }//GEN-LAST:event_goToCashierBtnActionPerformed

    private void nextStepButtonToAccept(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextStepButtonToAccept
        // Go to the next step, to accept.
        cl2.show(cashierPanel, "accept");
    }//GEN-LAST:event_nextStepButtonToAccept

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

    private void nextStepButtonToAccept1(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextStepButtonToAccept1
        // TODO add your handling code here:
    }//GEN-LAST:event_nextStepButtonToAccept1

    private void backButtonToPersonalInfo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonToPersonalInfo
        cl2.show(productPanel, "personalInfoDelivery");
    }//GEN-LAST:event_backButtonToPersonalInfo

    private void cashierBackToPayment(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cashierBackToPayment
        cl2.show(productPanel, "payment");
    }//GEN-LAST:event_cashierBackToPayment

    private void acceptBackToShoppingCart(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptBackToShoppingCart
        cl2.show(productPanel, "shoppingCart");
    }//GEN-LAST:event_acceptBackToShoppingCart

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
    private javax.swing.JButton backButtonToPayment;
    private javax.swing.JButton backButtonToPersonalInfo;
    private javax.swing.JButton backButtonToShoppingCart;
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
    private javax.swing.JLabel cartHeadlineLabel;
    private javax.swing.JLabel cartImage;
    private javax.swing.JPanel cartPanel;
    private javax.swing.JScrollPane cartPanelScrollPane;
    private javax.swing.JTextField cashierAddressTF;
    private javax.swing.JTextField cashierEmailTF;
    private javax.swing.JTextField cashierFirstNameTF;
    private javax.swing.JTextField cashierLastNameTF;
    private javax.swing.JTextField cashierOtherAddressTF;
    private javax.swing.JTextField cashierOtherPostCodeTF;
    private javax.swing.JPanel cashierPanel;
    private javax.swing.JTextField cashierPhoneNumberTF;
    private javax.swing.JTextField cashierPostCodeTF;
    private javax.swing.JLabel category1MenuLabel;
    private javax.swing.JLabel category2MenuLabel;
    private javax.swing.JLabel category3MenuLabel;
    private javax.swing.JLabel category4MenuLabel;
    private javax.swing.JLabel category5MenuLabel;
    private javax.swing.JLabel category6MenuLabel;
    private javax.swing.JLabel categoryFeatureLabel;
    private javax.swing.JPanel categoryFeaturePanel;
    private javax.swing.JPanel categoryPanel;
    private javax.swing.JPanel checkPaymentPanel;
    private javax.swing.JPanel checkPersonalInfoPanel;
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
    private javax.swing.JButton dealsBtn;
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel krLabel;
    private javax.swing.JLabel lastNameLabel;
    private javax.swing.JLabel lastNameLabel1;
    private javax.swing.JTextField lastNameTextField;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JPanel leftUpperPanel;
    private javax.swing.JPanel logoPanel;
    private javax.swing.JPanel myAccountContentPanel;
    private javax.swing.JPanel myAccountPanel;
    private javax.swing.JPanel myAccountTopPanel;
    private javax.swing.JButton myFavoritesBtn;
    private javax.swing.JPanel myFavoritesViewPanel;
    private javax.swing.JButton myShoppingBagsBtn;
    private javax.swing.JPanel myShoppingBagsViewPanel;
    private javax.swing.JButton nextStepButtonToAccept;
    private javax.swing.JButton nextStepButtonToAccept1;
    private javax.swing.JButton nextStepButtonToPayment;
    private javax.swing.JButton nextStepButtonToPersonalInfo;
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
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JPanel searchResultPanel;
    private javax.swing.JLabel separator1;
    private javax.swing.JLabel separator2;
    private javax.swing.JLabel separator3;
    private javax.swing.JLabel separator4;
    private javax.swing.JLabel separator5;
    private javax.swing.JLabel separator8;
    private javax.swing.JPanel shoppingCartPanel;
    private javax.swing.JPanel startViewPanel;
    private javax.swing.JLabel titleNameLabel;
    private javax.swing.JPanel topPanel;
    private javax.swing.JLabel totalPriceLabel;
    private javax.swing.JLabel tpCartContentsNumber;
    private javax.swing.JLabel tpCartSumLabel;
    private javax.swing.JLabel tpChangeUserLabel;
    private javax.swing.JLabel tpGoToCashierLabel;
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
