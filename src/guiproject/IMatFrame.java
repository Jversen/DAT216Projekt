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
            cartContentsPanel.add(new JLabel(cartContentList.get(i).getProduct().getName()));
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
                
                GridLayout layout = new GridLayout(3, gpCon.products.size());
                              
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
                gpCon.listCatProds("POD");
                displayGroceries();
                break;
            case "Bröd":
                gpCon.listCatProds("BREAD");
                displayGroceries();
                break;
            case "Bär":
                gpCon.listCatProds("BERRY");
                displayGroceries();
                break;
            case "Citrusfrukter":
                gpCon.listCatProds("CITRUS_FRUIT");
                displayGroceries();
                break;
                
            case "Varma drycker":
                gpCon.listCatProds("HOT_DRINKS");
                displayGroceries();
                break;
            case "Kalla drycker":
                gpCon.listCatProds("COLD_DRINKS");
                displayGroceries();
                break;
                
            case "Exotiska frukter":
                gpCon.listCatProds("EXOTIC_FRUIT");
                displayGroceries();
                break;
            case "Fisk":
                gpCon.listCatProds("FISH");
                displayGroceries();
                break;
                
            case "Grönsaksfrukter":
                gpCon.listCatProds("VEGETABLE_FRUIT");
                displayGroceries();
                break;
            case "Kål":
                gpCon.listCatProds("CABBAGE");
                displayGroceries();
                break;
            case "Kött":
                gpCon.listCatProds("MEAT");
                displayGroceries();
                break;
            case "Mejeriprodukter":
                gpCon.listCatProds("DAIRIES");
                displayGroceries();
                break;
            case "Meloner":
                gpCon.listCatProds("MELONS");
                displayGroceries();
                break;
           
            case "Mjöl, socker och salt":
                gpCon.listCatProds("FLOUR_SUGAR_SALT");
                displayGroceries();
                break;
            case "Nötter och frön":
                gpCon.listCatProds("NUTS_AND_SEEDS");
                displayGroceries();
                break;
            case "Pasta":
                gpCon.listCatProds("PASTA");
                displayGroceries();
                break;
            case "Potatis och ris":
                gpCon.listCatProds("POTATO_RICE");
                displayGroceries();
                break;
            case "Rotfrukter":
                gpCon.listCatProds("ROOT_VEGETABLE");
                displayGroceries();
                break;
             
            case "Frukt":
                gpCon.listCatProds("FRUIT");
                displayGroceries();
                break;
            case "Sötsaker":
                gpCon.listCatProds("SWEET");
                displayGroceries();
                break;
            case "Örter":
                gpCon.listCatProds("HERB");
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
        jLabel12 = new javax.swing.JLabel();
        tpMyAccountLabel = new javax.swing.JLabel();
        tpChangeUserLabel = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
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
        addressPanel = new javax.swing.JPanel();
        adress1Label1 = new javax.swing.JLabel();
        address1TextField1 = new javax.swing.JTextField();
        adress2TextField1 = new javax.swing.JTextField();
        adress2Label1 = new javax.swing.JLabel();
        codeLabel1 = new javax.swing.JLabel();
        postCodeTextField1 = new javax.swing.JTextField();
        cityTextField1 = new javax.swing.JTextField();
        cityLabel1 = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        myAccountTopPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        titleNameLabel = new javax.swing.JLabel();
        logoPanel = new javax.swing.JPanel();
        iMatLabel = new javax.swing.JLabel();
        cartPanel = new javax.swing.JPanel();
        cartHeadlineLabel = new javax.swing.JLabel();
        goToCashierBtn = new javax.swing.JButton();
        cartPanelScrollPane = new javax.swing.JScrollPane();
        cartContentsPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        leftPanel.setBackground(new java.awt.Color(204, 0, 0));

        leftUpperPanel.setBackground(new java.awt.Color(51, 255, 51));
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

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guiKundvagn.png"))); // NOI18N

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

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guiProfile.png"))); // NOI18N

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
                .addComponent(jLabel12)
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
                .addComponent(jLabel16)
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
                                .addComponent(jLabel16)
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
                                .addComponent(jLabel12)))))
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
                .addContainerGap(168, Short.MAX_VALUE))
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
                .addGap(0, 607, Short.MAX_VALUE))
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
                .addGap(0, 607, Short.MAX_VALUE))
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
                .addGap(0, 607, Short.MAX_VALUE))
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
                .addGap(0, 607, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 265, Short.MAX_VALUE)
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
                .addContainerGap(614, Short.MAX_VALUE))
        );

        featurePanel.add(categoryFeaturePanel, "categoryFeaturePanel");

        searchResultPanel.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        itemShower.setLayout(new java.awt.GridLayout(1, 2));
        jPanel1.add(itemShower);

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
                .addContainerGap(27, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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
        deliveryCheckBox.setEnabled(false);
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
                .addContainerGap(45, Short.MAX_VALUE))
        );

        separator3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/redSeparator.png"))); // NOI18N

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
                        .addComponent(deliveryTimeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deliveryDayComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, deliveryPanelLayout.createSequentialGroup()
                        .addComponent(deliverToMyAdressPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(deliveryPanelLayout.createSequentialGroup()
                        .addGroup(deliveryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(personalInfoLabel1)
                            .addComponent(separator3))
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(deliveryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(deliveryPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(addressPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(deliverToMyAdressPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(deliveryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(deliveryPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(addressPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        saveButton.setBackground(new java.awt.Color(255, 255, 255));
        saveButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        saveButton.setForeground(new java.awt.Color(0, 51, 0));
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

        javax.swing.GroupLayout myAccountContentPanelLayout = new javax.swing.GroupLayout(myAccountContentPanel);
        myAccountContentPanel.setLayout(myAccountContentPanelLayout);
        myAccountContentPanelLayout.setHorizontalGroup(
            myAccountContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, myAccountContentPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(personalInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addComponent(paymentInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(myAccountContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(deliveryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        myAccountContentPanelLayout.setVerticalGroup(
            myAccountContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myAccountContentPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(myAccountContentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(personalInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(myAccountContentPanelLayout.createSequentialGroup()
                        .addComponent(deliveryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(39, 39, 39)
                        .addComponent(saveButton))
                    .addComponent(paymentInfoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(184, Short.MAX_VALUE))
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

        logoPanel.setBackground(new java.awt.Color(204, 0, 0));
        logoPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoPanelMouseClicked(evt);
            }
        });

        iMatLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        iMatLabel.setText("iMat");
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

        cartPanel.setBackground(new java.awt.Color(204, 255, 255));

        cartHeadlineLabel.setText("Kundvagn");

        goToCashierBtn.setText("Gå till kassa");

        cartContentsPanel.setBackground(new java.awt.Color(204, 102, 255));
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
        gpCon.doSearch(searchField.getText());
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

    private void firstNameTextFieldFirstNameUpdate(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_firstNameTextFieldFirstNameUpdate
        gpCon.iMDH.getCustomer().setFirstName(firstNameTextField.getText());
    }//GEN-LAST:event_firstNameTextFieldFirstNameUpdate

    private void lastNameTextFieldLastNameUpdate(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lastNameTextFieldLastNameUpdate
        gpCon.iMDH.getCustomer().setLastName(lastNameTextField.getText());
    }//GEN-LAST:event_lastNameTextFieldLastNameUpdate

    private void address1TextFieldAddress1Update(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_address1TextFieldAddress1Update
        gpCon.iMDH.getCustomer().setAddress(address1TextField.getText());
    }//GEN-LAST:event_address1TextFieldAddress1Update

    private void postCodeTextFieldPostCodeUpdate(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_postCodeTextFieldPostCodeUpdate
        gpCon.iMDH.getCustomer().setPostCode(postCodeTextField.getText());
    }//GEN-LAST:event_postCodeTextFieldPostCodeUpdate

    private void emailTextFieldEmailUpdate(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailTextFieldEmailUpdate
        gpCon.iMDH.getCustomer().setEmail(emailTextField.getText());
    }//GEN-LAST:event_emailTextFieldEmailUpdate

    private void phoneNumberTextFieldphoneNumberUpdate(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_phoneNumberTextFieldphoneNumberUpdate
        gpCon.iMDH.getCustomer().setPhoneNumber(phoneNumberTextField.getText());
    }//GEN-LAST:event_phoneNumberTextFieldphoneNumberUpdate

    private void cardNumberTextFieldCardNumberUpdate(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cardNumberTextFieldCardNumberUpdate
        gpCon.iMDH.getCreditCard().setCardNumber(cardNumberTextField.getText());
    }//GEN-LAST:event_cardNumberTextFieldCardNumberUpdate

    private void cardTypeComboBoxCardTypeUpdate(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cardTypeComboBoxCardTypeUpdate
        gpCon.iMDH.getCreditCard().setCardType(cardTypeComboBox.getSelectedItem().toString());
    }//GEN-LAST:event_cardTypeComboBoxCardTypeUpdate

    private void validMonthComboBoxValidMonthUpdate(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validMonthComboBoxValidMonthUpdate
        gpCon.iMDH.getCreditCard().setValidMonth(validMonthComboBox.getSelectedIndex());
    }//GEN-LAST:event_validMonthComboBoxValidMonthUpdate

    private void validYearComboBoxValidYearUpdate(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validYearComboBoxValidYearUpdate
        gpCon.iMDH.getCreditCard().setValidYear(validYearComboBox.getSelectedIndex());
    }//GEN-LAST:event_validYearComboBoxValidYearUpdate

    private void verificationCodeTextFieldVerificationCodeUpdate(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_verificationCodeTextFieldVerificationCodeUpdate
        try{
            gpCon.iMDH.getCreditCard().setVerificationCode(Integer.parseInt(verificationCodeTextField.getText()));
        } catch(NumberFormatException nfe){
            System.out.println("Incorrect input.");
        }
    }//GEN-LAST:event_verificationCodeTextFieldVerificationCodeUpdate

    private void cardHolderTextFieldCardHolderUpdate(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cardHolderTextFieldCardHolderUpdate
        gpCon.iMDH.getCreditCard().setHoldersName(cardHolderTextField.getText());
    }//GEN-LAST:event_cardHolderTextFieldCardHolderUpdate

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

    private void address1TextField1AddressUpdate(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_address1TextField1AddressUpdate
        gpCon.iMDH.getCustomer().setPostAddress(address1TextField.getText());
    }//GEN-LAST:event_address1TextField1AddressUpdate

    private void postCodeTextField1PostCodeUpdate(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_postCodeTextField1PostCodeUpdate
        gpCon.iMDH.getInstance().getCustomer().setPostCode(postCodeTextField.getText());
    }//GEN-LAST:event_postCodeTextField1PostCodeUpdate

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
    private javax.swing.JTextField address1TextField;
    private javax.swing.JTextField address1TextField1;
    private javax.swing.JTextField address2TextField;
    private javax.swing.JPanel addressPanel;
    private javax.swing.JLabel adress1Label;
    private javax.swing.JLabel adress1Label1;
    private javax.swing.JLabel adress2Label;
    private javax.swing.JLabel adress2Label1;
    private javax.swing.JTextField adress2TextField1;
    private javax.swing.JLabel cardHolderNameLabel;
    private javax.swing.JTextField cardHolderTextField;
    private javax.swing.JLabel cardNumberLabel;
    private javax.swing.JTextField cardNumberTextField;
    private javax.swing.JComboBox cardTypeComboBox;
    private javax.swing.JLabel cardTypeLabel;
    private javax.swing.JPanel cartContentsPanel;
    private javax.swing.JLabel cartHeadlineLabel;
    private javax.swing.JPanel cartPanel;
    private javax.swing.JScrollPane cartPanelScrollPane;
    private javax.swing.JLabel category1MenuLabel;
    private javax.swing.JLabel category2MenuLabel;
    private javax.swing.JLabel category3MenuLabel;
    private javax.swing.JLabel category4MenuLabel;
    private javax.swing.JLabel category5MenuLabel;
    private javax.swing.JLabel category6MenuLabel;
    private javax.swing.JLabel categoryFeatureLabel;
    private javax.swing.JPanel categoryFeaturePanel;
    private javax.swing.JPanel categoryPanel;
    private javax.swing.JLabel cityLabel;
    private javax.swing.JLabel cityLabel1;
    private javax.swing.JTextField cityTextField;
    private javax.swing.JTextField cityTextField1;
    private javax.swing.JLabel codeLabel1;
    private javax.swing.JButton dealsBtn;
    private javax.swing.JLabel dealsLabel;
    private javax.swing.JPanel dealsViewPanel;
    private javax.swing.JLabel deliverToMyAddressLabel;
    private javax.swing.JPanel deliverToMyAdressPanel;
    private javax.swing.JCheckBox deliveryCheckBox;
    private javax.swing.JLabel deliveryDateLabel;
    private javax.swing.JComboBox deliveryDayComboBox;
    private javax.swing.JPanel deliveryPanel;
    private javax.swing.JComboBox deliveryTimeComboBox;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel expDateSlashLabel;
    private javax.swing.JLabel expirationDateLabel;
    private javax.swing.JPanel featurePanel;
    private javax.swing.JLabel firstNameLabel;
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
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lastNameLabel;
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
    private javax.swing.JLabel paymentInfoLabel;
    private javax.swing.JPanel paymentInfoPanel;
    private javax.swing.JLabel personalInfoLabel;
    private javax.swing.JLabel personalInfoLabel1;
    private javax.swing.JPanel personalInfoPanel;
    private javax.swing.JLabel phoneNumberLabel;
    private javax.swing.JTextField phoneNumberTextField;
    private javax.swing.JLabel popularLabel;
    private javax.swing.JLabel postCodeLabel;
    private javax.swing.JTextField postCodeTextField;
    private javax.swing.JTextField postCodeTextField1;
    private javax.swing.JLabel recentlyBoughtLabel;
    private javax.swing.JButton recipesBtn;
    private javax.swing.JPanel recipesViewPanel;
    private javax.swing.JButton saveButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchField;
    private javax.swing.JPanel searchResultPanel;
    private javax.swing.JLabel separator1;
    private javax.swing.JLabel separator2;
    private javax.swing.JLabel separator3;
    private javax.swing.JPanel startViewPanel;
    private javax.swing.JLabel titleNameLabel;
    private javax.swing.JPanel topPanel;
    private javax.swing.JLabel tpCartContentsNumber;
    private javax.swing.JLabel tpCartSumLabel;
    private javax.swing.JLabel tpChangeUserLabel;
    private javax.swing.JLabel tpGoToCashierLabel;
    private javax.swing.JLabel tpMyAccountLabel;
    private javax.swing.JButton updCartBtn;
    private javax.swing.JComboBox validMonthComboBox;
    private javax.swing.JComboBox validYearComboBox;
    private javax.swing.JLabel verificationCodeLabel;
    private javax.swing.JTextField verificationCodeTextField;
    // End of variables declaration//GEN-END:variables
}
