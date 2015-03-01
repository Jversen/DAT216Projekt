/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiproject;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import se.chalmers.ait.dat215.project.ProductCategory;

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
    
    int clickCounter = 0;
    public int klick1 = 0;
    
//    CategoryPanel catPanel = new CategoryPanel();
    /**
     * Creates new form iMatFrame
     */

    public IMatFrame() {
        initComponents();
        
        revalidate();
//        for (ProductCategory c : ProductCategory.values())
//            
//         categoryArrayList.add(new JLabel(c.toString()));
//            System.out.println(c);
        
        //System.out.println(s);
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
                        klick1++;
                    System.out.println("klickare skapat: " + klick1);   
                        allCategoryArrayList.get(i).addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                    
                                categoryLabelMouseClicked(evt);
                            }
                        });
                    }
                        
                    for (int i = 0; i<allSubCategoryArrayList.size(); i++){
//                       klick1++;
//                    System.out.println("klickare skapat: " + klick1);     
                        allSubCategoryArrayList.get(i).addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                    
                                categoryLabelMouseClicked(evt);
                            }
                        });
//                    
////                                placeCategories();
//           
//                
                    }
                    placeCategories();
    }
    

    private void placeCategories(){
        
//        allCategoryArrayList.clear();
//        System.out.println(allCategoryArrayList.size());
        
        
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
        
//        clickCounter++;
//        System.out.println("klickat?: " + clickCounter);
        JLabel tempLabel1 = (JLabel)evt.getSource();
        System.out.println(tempLabel1.getText());
//        tempLabel1 = new JLabel(tempLabel1.getText());     //x Skapar temporär label med samma text som den klickade kategorin.
        
        categoryPanel.removeAll();
        allCategoryArrayList.clear();
        for (int i = 0; i<headCategoryArrayList.size(); i++){
            allCategoryArrayList.add(headCategoryArrayList.get(i));
        }
//        placeCategories();
        System.out.println(tempLabel1.getText());
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
                categoryFeatureLabel.setText(tempLabel1.getText());
                cl.show(featurePanel, "categoryFeaturePanel");
                break;
            case "Bröd":
                gpCon.listCatProds("BREAD");
                categoryFeatureLabel.setText(tempLabel1.getText());
                cl.show(featurePanel, "categoryFeaturePanel");
                break;
            case "Bär":
                gpCon.listCatProds("BERRY");
                categoryFeatureLabel.setText(tempLabel1.getText());
                cl.show(featurePanel, "categoryFeaturePanel");
                break;
            case "Citrusfrukter":
                gpCon.listCatProds("CITRUS_FRUIT");
                categoryFeatureLabel.setText(tempLabel1.getText());
                cl.show(featurePanel, "categoryFeaturePanel");
                break;
                
            case "Varma drycker":
                gpCon.listCatProds("HOT_DRINKS");
                categoryFeatureLabel.setText(tempLabel1.getText());
                cl.show(featurePanel, "categoryFeaturePanel");
                break;
            case "Kalla drycker":
                gpCon.listCatProds("COLD_DRINKS");
                categoryFeatureLabel.setText(tempLabel1.getText());
                cl.show(featurePanel, "categoryFeaturePanel");
                break;
                
            case "Exotiska frukter":
                gpCon.listCatProds("EXOTIC_FRUIT");
                categoryFeatureLabel.setText(tempLabel1.getText());
                cl.show(featurePanel, "categoryFeaturePanel");
                break;
            case "Fisk":
                gpCon.listCatProds("FISH");
                categoryFeatureLabel.setText(tempLabel1.getText());
                cl.show(featurePanel, "categoryFeaturePanel");
                break;
                
            case "Grönsaksfrukter":
                gpCon.listCatProds("VEGETABLE_FRUIT");
                categoryFeatureLabel.setText(tempLabel1.getText());
                cl.show(featurePanel, "categoryFeaturePanel");
                break;
            case "Kål":
                gpCon.listCatProds("CABBAGE");
                categoryFeatureLabel.setText(tempLabel1.getText());
                cl.show(featurePanel, "categoryFeaturePanel");
                break;
            case "Kött":
                gpCon.listCatProds("MEAT");
                categoryFeatureLabel.setText(tempLabel1.getText());
                cl.show(featurePanel, "categoryFeaturePanel");
                break;
            case "Mejeriprodukter":
                gpCon.listCatProds("DAIRIES");
                categoryFeatureLabel.setText(tempLabel1.getText());
                cl.show(featurePanel, "categoryFeaturePanel");
                break;
            case "Meloner":
                gpCon.listCatProds("MELONS");
                categoryFeatureLabel.setText(tempLabel1.getText());
                cl.show(featurePanel, "categoryFeaturePanel");
                break;
           
            case "Mjöl, socker och salt":
                gpCon.listCatProds("FLOUR_SUGAR_SALT");
                categoryFeatureLabel.setText(tempLabel1.getText());
                cl.show(featurePanel, "categoryFeaturePanel");
                break;
            case "Nötter och frön":
                gpCon.listCatProds("NUTS_AND_SEEDS");
                categoryFeatureLabel.setText(tempLabel1.getText());
                cl.show(featurePanel, "categoryFeaturePanel");
                break;
            case "Pasta":
                gpCon.listCatProds("PASTA");
                categoryFeatureLabel.setText(tempLabel1.getText());
                cl.show(featurePanel, "categoryFeaturePanel");
                break;
            case "Potatis och ris":
                gpCon.listCatProds("POTATO_RICE");
                categoryFeatureLabel.setText(tempLabel1.getText());
                cl.show(featurePanel, "categoryFeaturePanel");
                break;
            case "Rotfrukter":
                gpCon.listCatProds("ROOT_VEGETABLE");
                categoryFeatureLabel.setText(tempLabel1.getText());
                cl.show(featurePanel, "categoryFeaturePanel");
                break;
             
            case "Frukt":
                gpCon.listCatProds("FRUIT");
                categoryFeatureLabel.setText(tempLabel1.getText());
                cl.show(featurePanel, "categoryFeaturePanel");
                break;
            case "Sötsaker":
                gpCon.listCatProds("SWEET");
                categoryFeatureLabel.setText(tempLabel1.getText());
                cl.show(featurePanel, "categoryFeaturePanel");
                break;
            case "Örter":
                gpCon.listCatProds("HERB");
                categoryFeatureLabel.setText(tempLabel1.getText());
                cl.show(featurePanel, "categoryFeaturePanel");
                break;
                    
                    default:
                break;
                
                
        }
//        System.out.println("slut på case");
        placeCategories();
//        categoryArrayList.add(tempLabel1);           //x Lägger till denna i kategorilistan.
//        leftPanel.add(headCategoryArrayList);                  //x Lägger till denna i vänsterpanelen, men det verkar ske någon konstig
                                                    //rotation istället för att den synliga listan blir längre.
//        categoryFeaturePanel cfp = new categoryFeaturePanel;
        revalidate();
        
        //System.out.println(categoryArrayList.size());
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
        lpSearchField = new javax.swing.JTextField();
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
        jButton1 = new javax.swing.JButton();
        myShoppingBagsBtn = new javax.swing.JButton();
        myFavoritesBtn = new javax.swing.JButton();
        recipesBtn = new javax.swing.JButton();
        dealsBtn = new javax.swing.JButton();
        historyBtn = new javax.swing.JButton();
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
        logoPanel = new javax.swing.JPanel();
        iMatLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        leftPanel.setBackground(new java.awt.Color(204, 0, 0));

        leftUpperPanel.setLayout(new java.awt.GridLayout(1, 1));

        lpSearchField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        lpSearchField.setText("Sök...");
        lpSearchField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lpSearchFieldMouseClicked(evt);
            }
        });
        leftUpperPanel.add(lpSearchField);

        categoryPanel.setBackground(new java.awt.Color(204, 0, 0));
        categoryPanel.setLayout(new java.awt.GridLayout(30, 1));

        javax.swing.GroupLayout leftPanelLayout = new javax.swing.GroupLayout(leftPanel);
        leftPanel.setLayout(leftPanelLayout);
        leftPanelLayout.setHorizontalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addComponent(leftUpperPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
            .addComponent(categoryPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        leftPanelLayout.setVerticalGroup(
            leftPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(leftPanelLayout.createSequentialGroup()
                .addComponent(leftUpperPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(categoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        topPanel.setBackground(new java.awt.Color(204, 0, 0));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guiKundvagn.png"))); // NOI18N

        tpMyAccountLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tpMyAccountLabel.setText("Mitt konto");
        tpMyAccountLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        tpChangeUserLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tpChangeUserLabel.setText("Byt användare");
        tpChangeUserLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/guiProfile.png"))); // NOI18N

        tpCartContentsNumber.setText("Varor");

        tpCartSumLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tpCartSumLabel.setText("kr");

        tpGoToCashierLabel.setText("Gå till kassan");
        tpGoToCashierLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("XstVaror");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Xkr");

        jButton1.setText("Testknappen");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout topPanelLayout = new javax.swing.GroupLayout(topPanel);
        topPanel.setLayout(topPanelLayout);
        topPanelLayout.setHorizontalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(topPanelLayout.createSequentialGroup()
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(myShoppingBagsBtn)
                        .addGap(18, 18, 18)
                        .addComponent(myFavoritesBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(recipesBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dealsBtn))
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(historyBtn)
                .addGap(161, 161, 161)
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
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                                .addComponent(jLabel12)
                                .addGroup(topPanelLayout.createSequentialGroup()
                                    .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tpCartContentsNumber)
                                        .addComponent(jLabel2))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tpCartSumLabel)
                                        .addComponent(jLabel3))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(tpGoToCashierLabel))))))
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
                .addContainerGap(697, Short.MAX_VALUE))
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
                .addContainerGap(132, Short.MAX_VALUE))
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
                .addGap(0, 888, Short.MAX_VALUE))
        );
        myShoppingBagsViewPanelLayout.setVerticalGroup(
            myShoppingBagsViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myShoppingBagsViewPanelLayout.createSequentialGroup()
                .addComponent(fpMyShoppingBagsLabel)
                .addGap(0, 571, Short.MAX_VALUE))
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
                .addGap(0, 908, Short.MAX_VALUE))
        );
        myFavoritesViewPanelLayout.setVerticalGroup(
            myFavoritesViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(myFavoritesViewPanelLayout.createSequentialGroup()
                .addComponent(fpMyFavoritesLabel)
                .addGap(0, 571, Short.MAX_VALUE))
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
                .addGap(0, 983, Short.MAX_VALUE))
        );
        recipesViewPanelLayout.setVerticalGroup(
            recipesViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(recipesViewPanelLayout.createSequentialGroup()
                .addComponent(fpRecipesLabel)
                .addGap(0, 571, Short.MAX_VALUE))
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
                .addGap(0, 923, Short.MAX_VALUE))
        );
        dealsViewPanelLayout.setVerticalGroup(
            dealsViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dealsViewPanelLayout.createSequentialGroup()
                .addComponent(fpDealsLabel)
                .addGap(0, 571, Short.MAX_VALUE))
        );

        featurePanel.add(dealsViewPanel, "cardDeals");

        fpHistoryLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        fpHistoryLabel.setText("Historik");

        category1MenuLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        category1MenuLabel.setText("Kategori 1");
        category1MenuLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        category1MenuLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                category1MenuLabelMouseClicked(evt);
            }
        });

        category2MenuLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        category2MenuLabel.setText("Kategori 2");
        category2MenuLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        category3MenuLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        category3MenuLabel.setText("Kategori 3");
        category3MenuLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        category4MenuLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        category4MenuLabel.setText("Kategori 4");
        category4MenuLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        category5MenuLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        category5MenuLabel.setText("Kategori 5");
        category5MenuLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        category6MenuLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        category6MenuLabel.setText("Kategori 6");
        category6MenuLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

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
                .addGap(0, 977, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 230, Short.MAX_VALUE)
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
                .addContainerGap(980, Short.MAX_VALUE))
        );
        categoryFeaturePanelLayout.setVerticalGroup(
            categoryFeaturePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(categoryFeaturePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(categoryFeatureLabel)
                .addContainerGap(575, Short.MAX_VALUE))
        );

        featurePanel.add(categoryFeaturePanel, "categoryFeaturePanel");

        logoPanel.setBackground(new java.awt.Color(204, 0, 0));
        logoPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoPanelMouseClicked(evt);
            }
        });

        iMatLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        iMatLabel.setText("iMat");
        iMatLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

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
                    .addComponent(featurePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(topPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
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
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

        /**
         * tar bort texten "sök..." från sökrutan när denna markeras.
         * Ytterligare funktionalitet ej tillagd (texten bör komma tillbaka när
         * rutan avmarkerats etc.)
         */
    private void lpSearchFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lpSearchFieldMouseClicked
        lpSearchField.setText("");
    }//GEN-LAST:event_lpSearchFieldMouseClicked

    private void logoPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoPanelMouseClicked
        cl.show(featurePanel, "cardStart");
    }//GEN-LAST:event_logoPanelMouseClicked

    private void category1MenuLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_category1MenuLabelMouseClicked
        revalidate();
    }//GEN-LAST:event_category1MenuLabelMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        JLabel hej = new JLabel("hej");
        
        
        revalidate();
    }//GEN-LAST:event_jButton1ActionPerformed

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
    private javax.swing.JLabel category1MenuLabel;
    private javax.swing.JLabel category2MenuLabel;
    private javax.swing.JLabel category3MenuLabel;
    private javax.swing.JLabel category4MenuLabel;
    private javax.swing.JLabel category5MenuLabel;
    private javax.swing.JLabel category6MenuLabel;
    private javax.swing.JLabel categoryFeatureLabel;
    private javax.swing.JPanel categoryFeaturePanel;
    private javax.swing.JPanel categoryPanel;
    private javax.swing.JButton dealsBtn;
    private javax.swing.JLabel dealsLabel;
    private javax.swing.JPanel dealsViewPanel;
    private javax.swing.JPanel featurePanel;
    private javax.swing.JLabel fpDealsLabel;
    private javax.swing.JLabel fpHistoryLabel;
    private javax.swing.JLabel fpMyFavoritesLabel;
    private javax.swing.JLabel fpMyShoppingBagsLabel;
    private javax.swing.JLabel fpRecipesLabel;
    private javax.swing.JButton historyBtn;
    private javax.swing.JPanel historyViewPanel;
    private javax.swing.JLabel iMatLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JPanel leftUpperPanel;
    private javax.swing.JPanel logoPanel;
    private javax.swing.JTextField lpSearchField;
    private javax.swing.JButton myFavoritesBtn;
    private javax.swing.JPanel myFavoritesViewPanel;
    private javax.swing.JButton myShoppingBagsBtn;
    private javax.swing.JPanel myShoppingBagsViewPanel;
    private javax.swing.JLabel popularLabel;
    private javax.swing.JLabel recentlyBoughtLabel;
    private javax.swing.JButton recipesBtn;
    private javax.swing.JPanel recipesViewPanel;
    private javax.swing.JPanel startViewPanel;
    private javax.swing.JPanel topPanel;
    private javax.swing.JLabel tpCartContentsNumber;
    private javax.swing.JLabel tpCartSumLabel;
    private javax.swing.JLabel tpChangeUserLabel;
    private javax.swing.JLabel tpGoToCashierLabel;
    private javax.swing.JLabel tpMyAccountLabel;
    // End of variables declaration//GEN-END:variables
}
