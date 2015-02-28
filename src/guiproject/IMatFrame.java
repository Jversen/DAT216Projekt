/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiproject;

import java.awt.CardLayout;
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
    private ArrayList<JLabel> headCategoryArrayList = new ArrayList<JLabel>();
    
    private ArrayList<JLabel> breadCategoryArrayList = new ArrayList<JLabel>();
    private ArrayList<JLabel> drinkCategoryArrayList = new ArrayList<JLabel>();
    private ArrayList<JLabel> fishCategoryArrayList = new ArrayList<JLabel>();
    private ArrayList<JLabel> fruitVegsCategoryArrayList = new ArrayList<JLabel>();
    private ArrayList<JLabel> spiceCategoryArrayList = new ArrayList<JLabel>();
    private ArrayList<JLabel> meatCategoryArrayList = new ArrayList<JLabel>();
    private ArrayList<JLabel> dairiesCategoryArrayList = new ArrayList<JLabel>();
    private ArrayList<JLabel> sweetsCategoryArrayList = new ArrayList<JLabel>();
    
    CategoryPanel catPanel = new CategoryPanel();
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
         * OBS temporär lösning, byt ut när backend erhållits.
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
            
        
        JLabel headCategoryDrinks = new JLabel("Drycker");
        headCategoryArrayList.add(headCategoryDrinks);
        
            JLabel subCategoryHotDrinksLabel = new JLabel("Varma drycker");
            JLabel subCategoryColdDrinksLabel = new JLabel("Kalla drycker");
            
        drinkCategoryArrayList.add(subCategoryHotDrinksLabel);
        drinkCategoryArrayList.add(subCategoryColdDrinksLabel);
        
        JLabel headCategoryFish = new JLabel("Fisk");
        headCategoryArrayList.add(headCategoryFish);
        
            JLabel subCategoryFishLabel = new JLabel("Fisk");
            
        fishCategoryArrayList.add(subCategoryFishLabel);

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
        
        JLabel headCategorySpice = new JLabel("Kryddor");
        headCategoryArrayList.add(headCategorySpice);
        
            JLabel subCategoryHerbLabel = new JLabel("Örter");
        fishCategoryArrayList.add(subCategoryHerbLabel);
        
        JLabel headCategoryMeat = new JLabel("Kött");
        headCategoryArrayList.add(headCategoryMeat);
        
            JLabel subCategoryMeatLabel = new JLabel("Kött");
        meatCategoryArrayList.add(subCategoryMeatLabel);
        
        JLabel headCategoryDairies = new JLabel("Mejeriprodukter");
        headCategoryArrayList.add(headCategoryDairies);
        
            JLabel category12Label = new JLabel("Mejeriprodukter");
        dairiesCategoryArrayList.add(category12Label);
        
        JLabel headCategorySweets = new JLabel("Sötsaker");
        headCategoryArrayList.add(headCategorySweets);
        
        JLabel category20Label = new JLabel("Sötsaker");
        sweetsCategoryArrayList.add(category20Label);
        
                                          
        
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
        
        
        
        
        
        
//        categoryArrayList.add(category1Label);
//        categoryArrayList.add(category2Label);
//        categoryArrayList.add(category3Label);
//        categoryArrayList.add(category4Label);
//        categoryArrayList.add(category5Label);
//        categoryArrayList.add(category6Label);
//        categoryArrayList.add(category7Label);
//        categoryArrayList.add(category8Label);
//        categoryArrayList.add(category9Label);
//        categoryArrayList.add(category10Label);
//        categoryArrayList.add(category11Label);
//        categoryArrayList.add(category12Label);
//        categoryArrayList.add(category13Label);
//        categoryArrayList.add(category14Label);
//        categoryArrayList.add(category15Label);
//        categoryArrayList.add(category16Label);
//        categoryArrayList.add(category17Label);
//        categoryArrayList.add(category18Label);
//        categoryArrayList.add(category19Label);
//        categoryArrayList.add(category20Label);
//        categoryArrayList.add(category21Label);
        
//        for (int = 1; i<categoryArrayList; i++){
//        
//        categoryArrayList.add()        
//    }
        /**
         * //Lägger varje kategoris label i en ArrayList
         */
//        categoryArrayList.add(0, category1Label);
//        categoryArrayList.add(1, category2Label);
//        categoryArrayList.add(2, category3Label);
//        categoryArrayList.add(3, category4Label);
//        categoryArrayList.add(4, category5Label);
//        categoryArrayList.add(5, category5Label);
//        categoryArrayList.add(6, category6Label);
        
        /**
         * Lägger varje kategori-label till vänsterpanelen och ger dem klickegenskaper etc.
         */
//        System.out.println(categoriesXX.categories.size());
//         for (int i = 0; i<categoriesXX.categories.size(); i++){
//            
//            this.add(new JLabel(categoriesXX.categories.get(i).toString()));
//            revalidate();
//        }
        
        addCategories();
        
        
        
        
    }
    
    /**
     * 
     * oanvänd än så länge...
     */
    private void addCategories(){
            for (int i = 0; i<headCategoryArrayList.size(); i++){
            
            categoryPanel.add(headCategoryArrayList.get(i));
            headCategoryArrayList.get(i).setHorizontalAlignment(SwingConstants.RIGHT);
            headCategoryArrayList.get(i).setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            
            /**
             * Ofärdigt försök att tillskriva kategori-labels klickegenskaper.
             * Tillhörande underkategorier ska läggas till categoryArrayList.
             * Lite osäker på syntax här. Vill dessutom helst få med index i till metoden.
             */
            headCategoryArrayList.get(i).addMouseListener(new java.awt.event.MouseAdapter() {
           
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    categoryLabelMouseClicked(evt);
                }
            });
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
        System.out.println(tempLabel1.getText());
//        tempLabel1 = new JLabel(tempLabel1.getText());     //x Skapar temporär label med samma text som den klickade kategorin.
        
        switch(tempLabel1.getText()){
            case "Bröd och torrvaror": 
                System.out.println("Brödkategori öppnad");
                for (int i = 0; i<breadCategoryArrayList.size(); i++){
                    
                    headCategoryArrayList.add(1+i, breadCategoryArrayList.get(i));
                    System.out.println(breadCategoryArrayList.get(i));
                }
                
                break;
            case "Kött": 
                System.out.println("Köttkategori öppnad");
                break;
        }
        categoryPanel.removeAll();
        addCategories();
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
        lpBagsLabel = new javax.swing.JLabel();
        lpFavoritesLabel = new javax.swing.JLabel();
        lpRecipesLabel = new javax.swing.JLabel();
        lpDealsLabel = new javax.swing.JLabel();
        lpHistoryLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
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
        empPanel = new javax.swing.JPanel();
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
        logoPanel = new javax.swing.JPanel();
        iMatLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        leftPanel.setBackground(new java.awt.Color(204, 0, 0));
        leftPanel.setLayout(new java.awt.GridLayout(2, 1));

        leftUpperPanel.setLayout(new java.awt.GridLayout(8, 1));

        lpSearchField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        lpSearchField.setText("Sök...");
        lpSearchField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lpSearchFieldMouseClicked(evt);
            }
        });
        leftUpperPanel.add(lpSearchField);

        lpBagsLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lpBagsLabel.setText("Mina matkassar");
        lpBagsLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lpBagsLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lpBagsLabelMouseClicked(evt);
            }
        });
        leftUpperPanel.add(lpBagsLabel);

        lpFavoritesLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lpFavoritesLabel.setText("Favoriter");
        lpFavoritesLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lpFavoritesLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lpFavoritesLabelMouseClicked(evt);
            }
        });
        leftUpperPanel.add(lpFavoritesLabel);

        lpRecipesLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lpRecipesLabel.setText("Recept");
        lpRecipesLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lpRecipesLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lpRecipesLabelMouseClicked(evt);
            }
        });
        leftUpperPanel.add(lpRecipesLabel);

        lpDealsLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lpDealsLabel.setText("Erbjudanden");
        lpDealsLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lpDealsLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lpDealsLabelMouseClicked(evt);
            }
        });
        leftUpperPanel.add(lpDealsLabel);

        lpHistoryLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lpHistoryLabel.setText("Historik");
        lpHistoryLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lpHistoryLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lpHistoryLabelMouseClicked(evt);
            }
        });
        leftUpperPanel.add(lpHistoryLabel);
        leftUpperPanel.add(jSeparator1);

        leftPanel.add(leftUpperPanel);

        categoryPanel.setLayout(new java.awt.GridLayout(30, 1));
        leftPanel.add(categoryPanel);

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
                        .addComponent(dealsBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(historyBtn)
                .addGap(230, 230, 230)
                .addComponent(jLabel12)
                .addGap(28, 28, 28)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tpGoToCashierLabel)
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2)
                            .addGroup(topPanelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tpCartSumLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tpCartContentsNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(84, 84, 84)
                .addComponent(jLabel16)
                .addGap(18, 18, 18)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tpMyAccountLabel)
                    .addComponent(tpChangeUserLabel)))
        );
        topPanelLayout.setVerticalGroup(
            topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, topPanelLayout.createSequentialGroup()
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(myShoppingBagsBtn)
                    .addComponent(myFavoritesBtn)
                    .addComponent(recipesBtn)
                    .addComponent(dealsBtn)
                    .addComponent(historyBtn))
                .addGap(49, 49, 49))
            .addGroup(topPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addGroup(topPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                                    .addComponent(tpGoToCashierLabel)))
                            .addComponent(jLabel16))
                        .addGap(49, 49, 49))
                    .addGroup(topPanelLayout.createSequentialGroup()
                        .addComponent(tpMyAccountLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tpChangeUserLabel)
                        .addGap(67, 67, 67))))
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

        empPanel.setBackground(new java.awt.Color(102, 255, 255));
        empPanel.setLayout(new java.awt.GridLayout(1, 1));

        javax.swing.GroupLayout startViewPanelLayout = new javax.swing.GroupLayout(startViewPanel);
        startViewPanel.setLayout(startViewPanelLayout);
        startViewPanelLayout.setHorizontalGroup(
            startViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(startViewPanelLayout.createSequentialGroup()
                .addGap(208, 208, 208)
                .addGroup(startViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dealsLabel)
                    .addComponent(recentlyBoughtLabel)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(startViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(popularLabel)))
                .addGap(138, 138, 138)
                .addComponent(empPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(323, Short.MAX_VALUE))
        );
        startViewPanelLayout.setVerticalGroup(
            startViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(startViewPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(startViewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(empPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(startViewPanelLayout.createSequentialGroup()
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
                        .addGap(0, 121, Short.MAX_VALUE)))
                .addContainerGap())
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
                .addGap(0, 960, Short.MAX_VALUE))
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
                .addGap(0, 980, Short.MAX_VALUE))
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
                .addGap(0, 1055, Short.MAX_VALUE))
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
                .addGap(0, 995, Short.MAX_VALUE))
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
                .addGap(0, 1049, Short.MAX_VALUE))
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
                    .addComponent(logoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(featurePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(topPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                    .addComponent(leftPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(featurePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void lpBagsLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lpBagsLabelMouseClicked
        cl.show(featurePanel, "cardBags");     
    }//GEN-LAST:event_lpBagsLabelMouseClicked

    private void lpFavoritesLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lpFavoritesLabelMouseClicked
        cl.show(featurePanel, "cardFavorites");
    }//GEN-LAST:event_lpFavoritesLabelMouseClicked

    private void lpRecipesLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lpRecipesLabelMouseClicked
        cl.show(featurePanel, "cardRecipes");
    }//GEN-LAST:event_lpRecipesLabelMouseClicked

    private void lpDealsLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lpDealsLabelMouseClicked
        cl.show(featurePanel, "cardDeals");
    }//GEN-LAST:event_lpDealsLabelMouseClicked

    private void lpHistoryLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lpHistoryLabelMouseClicked
        cl.show(featurePanel, "cardHistory");
    }//GEN-LAST:event_lpHistoryLabelMouseClicked

    private void logoPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoPanelMouseClicked
        cl.show(featurePanel, "cardStart");
    }//GEN-LAST:event_logoPanelMouseClicked

    private void category1MenuLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_category1MenuLabelMouseClicked
        //leftPanel.add(new JLabel("- Und.kat. 1"), category1MenuLabel.get ); // "Utvidgar" en matkategori genom att lägga till dess underkategorier i categoryArrayList.
        revalidate();
    }//GEN-LAST:event_category1MenuLabelMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       // CategoryPanel catPanel = new CategoryPanel();
        JLabel hej = new JLabel("hej");
        
        gpCon.guiAdd(empPanel, catPanel);
        gpCon.guiAdd(leftPanel, new JLabel("teest"));
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
    private javax.swing.JPanel categoryPanel;
    private javax.swing.JButton dealsBtn;
    private javax.swing.JLabel dealsLabel;
    private javax.swing.JPanel dealsViewPanel;
    private javax.swing.JPanel empPanel;
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JPanel leftPanel;
    private javax.swing.JPanel leftUpperPanel;
    private javax.swing.JPanel logoPanel;
    private javax.swing.JLabel lpBagsLabel;
    private javax.swing.JLabel lpDealsLabel;
    private javax.swing.JLabel lpFavoritesLabel;
    private javax.swing.JLabel lpHistoryLabel;
    private javax.swing.JLabel lpRecipesLabel;
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
