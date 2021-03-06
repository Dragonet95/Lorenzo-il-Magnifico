package it.polimi.ingsw.ps06.view;

// for Container

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
// for WindowAdapter
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import java.util.TimerTask;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRootPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.TransferHandler;
import javax.swing.UIManager;
import javax.swing.plaf.DesktopPaneUI;
import javax.swing.plaf.basic.BasicInternalFrameUI;

import it.polimi.ingsw.ps06.model.Types.ColorPalette;
import it.polimi.ingsw.ps06.model.Types.CouncilPrivilege;
import it.polimi.ingsw.ps06.model.events.BoardHasLoaded;
import it.polimi.ingsw.ps06.model.events.EventClose;
import it.polimi.ingsw.ps06.model.events.EventLeaderActivated;
import it.polimi.ingsw.ps06.model.events.EventLeaderDiscarded;
import it.polimi.ingsw.ps06.model.events.EventLeaderPlayed;
import it.polimi.ingsw.ps06.model.events.EventMemberPlaced;
import it.polimi.ingsw.ps06.model.events.EventMemberPlacedWithDoublePrivilege;
import it.polimi.ingsw.ps06.model.events.EventMemberPlacedWithPrivilege;
import it.polimi.ingsw.ps06.networking.messages.MessageObtainPersonalBoardStatus;
import it.polimi.ingsw.ps06.networking.messages.MessagePlayerPassed;
import it.polimi.ingsw.ps06.networking.messages.MessageTelegram;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


@SuppressWarnings("restriction")
public class BoardGUI extends Observable implements Board {
	
	private int width, escWidth;
	private int height, escHeight;
	private JLabel leaderBack;
	private Font mediumBold;
	private Font extraSmall;
	private boolean chatOpen=false;
	
	private JButton scrollTowers = new JButton();
	private JButton scrollOthers = new JButton();
	
	private JTextField playerInfo;
	private JTextField roundInfo;
	private JTextField resourcesInfo;
	private JTextField timerInfo = new JTextField("00");
	private JTextField actionsLog = new JTextField();
	
	private JButton escMenu1;
	private JButton escMenu2;
	private Timer timer = createTimer(1000);
	
	private int pvIndex;

    private Font fontBIG, fontMEDIUM, fontSMALL, fontbig;
    private JDesktopPane desktop;
    private JFrame desktopFrame;
    
    private JButton[] cards = new JButton[16];
    private JButton[] markets = new JButton[4];
    private JButton[] productions = new JButton[9];
    private JButton[] harvests = new JButton[9];
    private JButton[] players = new JButton[5];
    private JTextField[] playersCheck = new JTextField[5];
    private JButton[] placements = new JButton[16];
	
    private JButton[] production = new JButton[1];
    private JButton[] harvest = new JButton[1];
	
    private JButton[] councils = new JButton[16];
    private JButton[] dices = new JButton[3];
    private JButton[] orders = new JButton[5];
    private JTextField[] playersInfo = new JTextField[5];
    private JButton[] excommunications = new JButton[3];
    private JButton[] excommStones = new JButton[12];
	
    private JButton[] privileges = new JButton[5];
    private JButton[] council = new JButton[1];
	private JButton[] leaders = new JButton[4];
	private JButton servants = new JButton();
	
	//Componenti per chat
	private JButton chat = new JButton("Chat");
	private JButton send = new JButton();
	private JTextArea chatBox = new JTextArea();
	private JTextField message = new JTextField();
	private int privilegeCount = 0 ;
	
	private JTextField servantsCount = new JTextField();
	private int[] cardsCodes = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
    
    private JLabel membersLabel[] = new JLabel[4];
    private JButton[] members = new JButton[4];
    private double ratio;
    
    private JLabel dicesLabel[] = new JLabel[3];
    private JLabel excommunicationsLabel[] = new JLabel[5];
    private JLabel playersLabel[] = new JLabel[5];
    private JLabel leadersLabel[] = new JLabel[4];
    private JLabel leadersLabelFade[] = new JLabel[4];
    
    private JLabel marketCover1;
    private JLabel marketCover2;
    private JLabel productionCover;
    private JLabel harvestCover;
    
    private JButton pass = new JButton();
    
    private boolean[] leaderPlayed = new boolean[5];
    private boolean[] leaderTable = new boolean[5];
    private boolean[] leaderHand = new boolean[5];
    
    private String[] playersName = new String[5]; 
    
    private JInternalFrame towers;
    private JInternalFrame others;
    
    private int TimerValue=70;
    
    private boolean allowed = true;
    private boolean[] member = new boolean[4];
    private int playerID=0;
    private String playerColor="G";
    private int blackValue=-1;
    private int orangeValue=-1;
    private int whiteValue=-1;
    private int playerNumber=1;
    private int roundNumber=1;
    private int periodNumber=1;
    private int ex1=-1, ex2=-1, ex3=-1;
    private int harvestIndex=1, productionIndex=1, councilIndex=0;
    private int lead1=-1, lead2=-1, lead3=-1, lead4=-1;
    private int coinV=0, woodV=0, stoneV=0, servantV=0, victoryV=0, militaryV=0, faithV=0;
    private int usedMember;
    private int servantsCountNumber=0;
    
    private int excomm1Count=0, excomm2Count=0, excomm3Count=0;
    private Media hit2;
    private Media exc, sendA, messageA, behaviourA, hit, goldS, goldES;
    
    private PersonalViewGUI view= new PersonalViewGUI(0,this);
	private JFXPanel fxPanel = new JFXPanel();
	
    public enum Direction {
		LEFT,
		RIGHT,
		BOTTOM,
		TOP;
	}

    @Override
	public void show() throws IOException{
		
		try {
		    UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		 } catch (Exception e) { e.printStackTrace();}
		
		setBoard();	
		
		JFrame escFrame = new JFrame();
		
		desktopFrame = new JFrame();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		ratio= (screenSize.getWidth()/screenSize.getHeight());

	    //Due frame interni al desktop per la parte delle torri e la sezione rimanente
		towers = new JInternalFrame("frame", false, false, false, false);
	    towers.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
	    towers.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
	    
	    others = new JInternalFrame("frame", false, false, false, false);
	    others.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
	    others.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
	    
	    JInternalFrame personalBoard = new JInternalFrame("frame", false, false, false, false);
	    personalBoard.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
	    personalBoard.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
	    
	    
	    //Rimuovi la barra per muovere le finestre	    
	    towers.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
	    towers.setBorder(null);
	    BasicInternalFrameUI bi = (BasicInternalFrameUI)towers.getUI();
	    bi.setNorthPane(null);
	    
	    others.putClientProperty("JInternalFrame.isPalette", Boolean.TRUE);
	    others.setBorder(null);
	    BasicInternalFrameUI bi2 = (BasicInternalFrameUI)others.getUI();
	    bi2.setNorthPane(null);
	    
	    //Scalabilità delle immagini su vari schermi
		width = (int)((screenSize.getWidth()*75/100)*(1.377 / ratio));
		height = (int)(screenSize.getHeight()*75/100);
		
		escWidth=(int)(width*60/100);
        escHeight=(int)(height*80/100);
        
        extraSmall= new Font("Lucida Handwriting",Font.PLAIN,(int)(12*(screenSize.getHeight()/1080)) );
        fontSMALL = new Font("Lucida Handwriting",Font.PLAIN,(int)(20*(screenSize.getHeight()/1080)) );
		fontMEDIUM = new Font("Lucida Handwriting",Font.PLAIN,(int)(25*(screenSize.getHeight()/1080)) );
		mediumBold = new Font("Lucida Handwriting",Font.BOLD,(int)(25*(screenSize.getHeight()/1080)) );
		fontBIG = new Font("Lucida Handwriting",Font.PLAIN,(int)(40*(screenSize.getHeight()/1080)) );
		fontbig = new Font("Lucida Handwriting",Font.PLAIN,(int)(33*(screenSize.getHeight()/1080)) );
		
		//Caricamento e resize delle immagini
		JLabel board1Label = ImageHandler.setImage("resources/board1.jpg", 100, 100, width, height);
		JLabel board2Label = ImageHandler.setImage("resources/board2.jpg", 100, 100, width, height);
		JLabel board3Label = ImageHandler.setImage("resources/stanzaVuota.png", 60, 80, width, height);
		JLabel board4Label = ImageHandler.setImageScreen("resources/desktop.jpg", 100, 100, (int)screenSize.getWidth(), (int)screenSize.getHeight());

        getResources();
        
        playersLabel[0] = ImageHandler.setImage("resources/player/avatar1.jpg",7,9,width,height);
        playersLabel[1] = ImageHandler.setImage("resources/player/avatar2.jpg",7,9,width,height);
        playersLabel[2] = ImageHandler.setImage("resources/player/avatar3.jpg",7,9,width,height);
        playersLabel[3] = ImageHandler.setImage("resources/player/avatar4.jpg",7,9,width,height);
        playersLabel[4] = ImageHandler.setImage("resources/player/avatar5.jpg",7,9,width,height);

        
        marketCover1 = ImageHandler.setImage("resources/cover/cover1.png",10,12,width,height);
        marketCover2 = ImageHandler.setImage("resources/cover/cover3.png",10,12,width,height);
        productionCover = ImageHandler.setImage("resources/cover/cover2.png",25,13,width,height);
        harvestCover = ImageHandler.setImage("resources/cover/cover4.png",25,15,width,height);
        
        leaderBack = new JLabel();
        leaderBack = ImageHandler.setImageScreen("resources/leader/leaderBack.jpg", 9,(int)(13.23*ratio),width,height);
        		
        //Creazione DesktopPane con Background
        desktop = new JDesktopPane(){
			@Override
		    protected void paintComponent(Graphics g) {
		        super.paintComponent(g);
                g.drawImage( ((ImageIcon)(board4Label.getIcon())).getImage(), 0, 0, null);
		    }};
        
	    
        //Caricamento suoni del gioco
		
		String selectSound = "/menuselect.mp3";
	    String mediaURL2 = getClass().getResource(selectSound).toExternalForm();
		hit2 = new Media(mediaURL2);
			
		String exitSounds = "/exit.mp3";
	    String mediaURL3 = getClass().getResource(exitSounds).toExternalForm();
		Media exitSound = new Media(mediaURL3);
		
		String slideSounds = "/slide.mp3";
	    String mediaURL4 = getClass().getResource(slideSounds).toExternalForm();
		Media slideSound = new Media(mediaURL4);
		
		String switchSounds = "/effect1.mp3";
	    String mediaURL5 = getClass().getResource(switchSounds).toExternalForm();
		Media switchSound = new Media(mediaURL5);
		 
		String excommunicate = "/excommunicate.mp3";
		String mediaURL6 = getClass().getResource(excommunicate).toExternalForm();
		exc = new Media(mediaURL6);
		
		String sendS = "/send.mp3";
		String mediaURL7 = getClass().getResource(sendS).toExternalForm();
		sendA = new Media(mediaURL7);
		
		String messageS = "/message.mp3";
		String mediaURL8 = getClass().getResource(messageS).toExternalForm();
		messageA = new Media(mediaURL8);
		
		String behaviourS = "/behaviour.mp3";
		String mediaURL9 = getClass().getResource(behaviourS).toExternalForm();
		behaviourA = new Media(mediaURL9);
		
		String hoverSound = "/place.mp3";
		String mediaURL10 = getClass().getResource(hoverSound).toExternalForm();
		hit = new Media(mediaURL10);
		
		String goldSound = "/gold.mp3";
        String mediaURL11 = getClass().getResource(goldSound).toExternalForm();
		goldS = new Media(mediaURL11);
		
		String goldEffect = "/marketsell.mp3";
        String mediaURL12 = getClass().getResource(goldEffect).toExternalForm();
		goldES = new Media(mediaURL12);
		
        //Inizializzazione dei componenti
		
		playerInfo = new JTextField("Loading...");
		playerInfo.setLocation((int)(screenSize.getWidth()*3/100),0);
		playerInfo.setSize((int)screenSize.getWidth()*60/100,(int)screenSize.getHeight()*6/100);
		playerInfo.setOpaque(false);
		playerInfo.setEditable(false);
		playerInfo.setBorder(null);
		playerInfo.setFont(fontBIG);
		playerInfo.setForeground(Color.BLACK);
		
		roundInfo = new JTextField("Loading...");
		roundInfo.setLocation((int)screenSize.getWidth()*60/100,0);
		roundInfo.setSize((int)screenSize.getWidth()*29/100,(int)screenSize.getHeight()*6/100);
		roundInfo.setOpaque(false);
		roundInfo.setEditable(false);
		roundInfo.setBorder(null);
		roundInfo.setFont(fontBIG);
		roundInfo.setForeground(Color.BLACK);
		
		timerInfo = new JTextField("");
		timerInfo.setLocation((int)screenSize.getWidth()*89/100,0);
		timerInfo.setSize((int)screenSize.getWidth()*11/100,(int)screenSize.getHeight()*6/100);
		timerInfo.setOpaque(false);
		timerInfo.setEditable(false);
		timerInfo.setBorder(null);
		timerInfo.setFont(fontbig);
		timerInfo.setForeground(Color.BLACK);
		timerInfo.setHorizontalAlignment(JTextField.CENTER);
		
		resourcesInfo = new JTextField("Loading...");
		resourcesInfo.setLocation((int)screenSize.getWidth()*10/100,(int)screenSize.getHeight()*96/100);
		resourcesInfo.setSize((int)screenSize.getWidth()*80/100,(int)screenSize.getHeight()*4/100);
		resourcesInfo.setOpaque(false);
		resourcesInfo.setEditable(false);
		resourcesInfo.setBorder(null);
		resourcesInfo.setFont(fontMEDIUM);
		resourcesInfo.setForeground(Color.BLACK);
		resourcesInfo.setHorizontalAlignment(JTextField.CENTER);
		
		actionsLog.setLocation((int)screenSize.getWidth()*20/100,(int)(screenSize.getHeight()*86/100));
		actionsLog.setSize((int)screenSize.getWidth()*60/100,(int)screenSize.getHeight()*5/100);
		actionsLog.setOpaque(false);
		actionsLog.setEditable(false);
		actionsLog.setBorder(null);
		actionsLog.setFont(fontSMALL);
		actionsLog.setForeground(Color.BLACK);
		actionsLog.setHorizontalAlignment(JTextField.CENTER);
		
        scrollOthers.setLocation(width*96/100,height*45/100);
        scrollOthers.setSize(width*4/100,height*10/100);
        scrollOthers.setOpaque(false);
        scrollOthers.setContentAreaFilled(false);
        scrollOthers.setFocusPainted(false);
        scrollOthers.setDisabledIcon( scrollOthers.getIcon() );
        scrollOthers.setIcon(ImageHandler.setImage("resources/backRight.png",(int)3.7,(int)(3*ratio),width,height).getIcon());
        //scrollOthers.setBorderPainted(false);
        
        scrollOthers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	MediaPlayer mediaPlayer1 = new MediaPlayer(slideSound);
				mediaPlayer1.play();
            	Animations.Animation(others ,towers,Direction.RIGHT);
            	
            	scrollTowers.setEnabled(false);
            	scrollOthers.setEnabled(false);
            	
            	new java.util.Timer().schedule( 
            	        new java.util.TimerTask() {
            	            @Override
            	            public void run() {
            	            	scrollTowers.setEnabled(true);
                        		scrollOthers.setEnabled(true);
            	            }}, 3000 );}
            });

        
        scrollTowers.setLocation(0,height*45/100);
        scrollTowers.setSize(width*4/100,height*10/100);
        scrollTowers.setOpaque(false);
        scrollTowers.setContentAreaFilled(false);
        scrollTowers.setFocusPainted(false);
        scrollTowers.setDisabledIcon( scrollTowers.getIcon() );
        scrollTowers.setIcon(ImageHandler.setImage("resources/backLeft.png",(int)3.7,(int)(3*ratio),width,height).getIcon());
   
        
        scrollTowers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            	
            	
            	MediaPlayer mediaPlayer2 = new MediaPlayer(slideSound);
				mediaPlayer2.play();
            	Animations.Animation(towers ,others,Direction.LEFT);
            	
            	scrollTowers.setEnabled(false);
            	scrollOthers.setEnabled(false);
            	
            	new java.util.Timer().schedule( 
            	        new java.util.TimerTask() {
            	            @Override
            	            public void run() {
            	            	scrollTowers.setEnabled(true);
                        		scrollOthers.setEnabled(true);
            	            }}, 3000 );}
            });
         
        
        pass=new JButton("Passa");
        pass.setFont(fontSMALL);
        pass.setLocation((int)(width*9/100),(int)(height*124.5/100));
        pass.setSize((int)width*10/100,(int)height*4/100);
        pass.setOpaque(false);
        pass.setContentAreaFilled(false);
        pass.setFocusPainted(false);
        pass.setHorizontalAlignment(JTextField.CENTER);
        
        pass.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
        
            	MediaPlayer mediaPlayer3 = new MediaPlayer(hit2);
        		mediaPlayer3.play();
            	
		        setChanged();
				MessagePlayerPassed playerPass = new MessagePlayerPassed();
				notifyObservers(playerPass);
            }
        });
        
        servants.setLocation((int)(width*11.5/100),(int)(height*97/100));
        servants.setSize((int)width*5/100,(int)height*7/100);
        servants.setOpaque(false);
        servants.setContentAreaFilled(false);
        servants.setFocusPainted(false);
        servants.setHorizontalAlignment(JTextField.CENTER);
        servants.setDisabledIcon( servants.getIcon() );
        servants.setIcon(ImageHandler.setImage("resources/servant.png",4,7,width,height).getIcon());
		
        servants.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent evt)
            {
            	MediaPlayer mediaPlayer3 = new MediaPlayer(hit2);
        		mediaPlayer3.play();
        		
        		if(servantsCountNumber < servantV){
        			servantsCountNumber++;
        		}
        		else{ 
        			if(servantsCountNumber == servantV){
        				servantsCountNumber=0;
        			}
        		}
        		
        		servantsCount.setText(""+servantsCountNumber);
            }
            
        });
        
        servantsCount.setLocation((int)(width*16.5)/100,(int)(height*99/100));
        servantsCount.setSize((int)width*2/100,(int)height*3/100);
        servantsCount.setOpaque(false);
        servantsCount.setFont(fontMEDIUM);
        servantsCount.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        servantsCount.setText(""+servantsCountNumber);
        
        escMenu1 = new JButton("Ritorna al gioco");
        escMenu1.setLocation(escWidth*20/100,escHeight*20/100);
        escMenu1.setSize(escWidth*60/100,escHeight*10/100);
        escMenu1.setOpaque(false);
        escMenu1.setContentAreaFilled(false);
        escMenu1.setFocusPainted(false);
        escMenu1.setMargin(new Insets(0,0,0,5));
        escMenu1.setForeground(Color.BLACK);
        escMenu1.setFont(fontBIG);
        escFrame.add(escMenu1);
        
        escMenu2 = new JButton("Esci dal gioco");
        escMenu2.setLocation(escWidth*20/100,escHeight*40/100);
        escMenu2.setSize(escWidth*60/100,escHeight*10/100);
        escMenu2.setOpaque(false);
        escMenu2.setContentAreaFilled(false);
        escMenu2.setFocusPainted(false);
        escMenu2.setMargin(new Insets(0,0,0,5));
        escMenu2.setForeground(Color.BLACK);
        escMenu2.setFont(fontBIG);
        escFrame.add(escMenu2);

        escMenu1.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent evt)
            {
            	MediaPlayer mediaPlayer3 = new MediaPlayer(hit2);
        		mediaPlayer3.play();
				escFrame.dispose();
				
            }
            
        });
        
        escMenu2.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent evt)
            {
            	MediaPlayer mediaPlayer6 = new MediaPlayer(exitSound);
        		mediaPlayer6.play();
        		notifyExit();
            }
            
        });
        
        //Impostazioni dei bottoni di gioco        
        
    	members = initializeButtons(members);
        cards = initializeButtons(cards);
        markets = initializeButtons(markets);
        productions = initializeButtons(productions);
        harvests = initializeButtons(harvests);
        players = initializeButtons(players);
        playersCheck = initialize(playersCheck);
        placements = initializeButtons(placements);
        council = initializeButtons(council);
        harvest = initializeButtons(harvest);
        production = initializeButtons(production);
        playersInfo = initialize(playersInfo);
        
        
        councils = initializeButtons(councils);
        dices = initializeButtons(dices);
        orders = initializeButtons(orders);
        excommunications = initializeButtons(excommunications);
        excommStones = initializeButtons(excommStones);
        privileges = initializeButtons(privileges);
        leaders = initializeButtons(leaders);
        
        
        cards = locateCards(cards);
        members = locateMembers(members);
        dices = locateDices(dices);
        harvests = locateHarvests(harvests);
        productions = locateProductions(productions);
        players = locatePlayers(players);
        playersCheck = locatePlayersCheck(playersCheck);
        placements = locatePlacements(placements);
        
        councils = locateCouncils(councils);
        orders = locateOrders(orders);
        playersInfo = locatePlayersInfo(playersInfo);
        excommunications = locateExcommunications(excommunications);
        excommStones = locateExcommStones(excommStones);
        privileges = locatePrivileges(privileges);
        leaders = locateLeaders(leaders);
        
        for(int j=0; j<members.length; j++){members[j].setDisabledIcon( members[j].getIcon() );}
        
        markets[0].setLocation((int)(width*58/100),(int)(height*61/100));
		markets[0].setSize(width*7/100,height*9/100);
		
		markets[1].setLocation((int)(width*68/100),(int)(height*61/100));
		markets[1].setSize(width*7/100,height*9/100);
		
		markets[2].setLocation((int)(width*76/100),(int)(height*62/100));
		markets[2].setSize(width*11/100,height*14/100);
		
		markets[3].setLocation((int)(width*83.3/100),(int)(height*71.6/100));
		markets[3].setSize((int)(width*11.2/100),(int)(height*15.6/100));  
		
        council[0].setLocation((int)(width*49/100),(int)(height*7/100));
        council[0].setSize(width*29/100,height*15/100);
        council = set(council);

		production[0].setLocation((int)(width*14.5/100),(int)(height*62.5/100));
		production[0].setSize((int)(width*24.3/100),(int)(height*13.5/100));
		production = set(production);
		
		harvest[0].setLocation((int)(width*14.5/100),(int)(height*81/100));
		harvest[0].setSize(width*24/100,height*13/100);
		harvest = set(harvest);

	
        members = set(members);
        markets = set(markets);
        players = set(players);
        
        playersCheck = setFont(playersCheck);
        
        for(int j=0; j<playersCheck.length; j++){
        	
        	playersCheck[j].setFont(mediumBold);
        }
        
        productions = set(productions);
        harvests = set(harvests);
        placements = set(placements);
        privileges = set(privileges);
        
        cards = setLabels(cards);
        councils = setLabels(councils);
        dices = setLabels(dices);
        orders = setLabels(orders);
		excommunications = setLabels(excommunications);
		excommStones = setLabels(excommStones);
		leaders = setLabels(leaders);
		
		playersInfo = setFont(playersInfo);
		playersInfo[0].setForeground(new Color(216,35,35));
		playersInfo[1].setForeground(new Color(26,175,17));
		playersInfo[2].setForeground(new Color(25,153,227));
		playersInfo[3].setForeground(new Color(255,248,48));
		playersInfo[4].setForeground(Color.WHITE);
        
        for(int j=0; j<players.length; j++){players[j].setDisabledIcon( players[j].getIcon() );}
        players = fillButtons(players,playersLabel);
        cards = fillCards(cards);
        
        others.add(production[0]);
        others.add(harvest[0]);
        others.add(council[0]);
        
        for(int j=0; j<excommStones.length;j++){ others.add(excommStones[j]); }
        for(int j=0; j<members.length;j++){ desktop.add(members[j]); }
        for(int j=0; j<cards.length;j++){ towers.add(cards[j]); }
        for(int j=0; j<markets.length;j++){ others.add(markets[j]); }
        for(int j=0; j<productions.length;j++){ others.add(productions[j]); }
        for(int j=0; j<harvests.length;j++){ others.add(harvests[j]); }
        for(int j=0; j<councils.length;j++){ others.add(councils[j]); }
        for(int j=0; j<players.length;j++){ desktop.add(players[j]); }
        for(int j=0; j<players.length;j++){ players[j].setVisible(false); }
        for(int j=0; j<placements.length;j++){ towers.add(placements[j]); }
             
        for(int j=0; j<playersCheck.length;j++){ desktopFrame.add(playersCheck[j]); }
        for(int j=0; j<dices.length;j++){ others.add(dices[j]); }
        for(int j=0; j<orders.length;j++){ others.add(orders[j]); }
        for(int j=0; j<playersInfo.length;j++){ desktop.add(playersInfo[j]); }
        for(int j=0; j<excommunications.length;j++){ others.add(excommunications[j]); }
        for(int j=0; j<privileges.length;j++){ others.add(privileges[j]); }
        
        for(int j=0; j<privileges.length;j++){
        	privileges[j].setBorderPainted(false);
        	privileges[j].setEnabled(false);
        }

        if(players[0].isEnabled()){
	       	players[0].addMouseListener(new MouseAdapter()
	       	{        		
	       		public void mousePressed(MouseEvent evt)
		        {
		          	MediaPlayer mediaPlayer3 = new MediaPlayer(hit2);
		      		mediaPlayer3.play();
		       		view.close();
		       		showPersonalView(0);
	            }
        	});
       	}
        
        if(players[1].isEnabled()){
	       	players[1].addMouseListener(new MouseAdapter()
	       	{        		
	       		public void mousePressed(MouseEvent evt)
		        {
		          	MediaPlayer mediaPlayer3 = new MediaPlayer(hit2);
		      		mediaPlayer3.play();
		       		view.close();
		       		showPersonalView(1);
	            }
        	});
       	}
        
        if(players[2].isEnabled()){
	       	players[2].addMouseListener(new MouseAdapter()
	       	{        		
	       		public void mousePressed(MouseEvent evt)
		        {
		          	MediaPlayer mediaPlayer3 = new MediaPlayer(hit2);
		      		mediaPlayer3.play();
		       		view.close();
		       		showPersonalView(2);
	            }
        	});
       	}
        
        if(players[3].isEnabled()){
	       	players[3].addMouseListener(new MouseAdapter()
	       	{        		
	       		public void mousePressed(MouseEvent evt)
		        {
		          	MediaPlayer mediaPlayer3 = new MediaPlayer(hit2);
		      		mediaPlayer3.play();
		       		view.close();
		       		showPersonalView(3);
	            }
        	});
       	}
        
        if(players[4].isEnabled()){
	       	players[4].addMouseListener(new MouseAdapter()
	       	{        		
	       		public void mousePressed(MouseEvent evt)
		        {
		          	MediaPlayer mediaPlayer3 = new MediaPlayer(hit2);
		      		mediaPlayer3.play();
		       		view.close();
		       		showPersonalView(4);
	            }
        	});
       	}
        
        for(int j=0; j<4;j++){
	        if(members[j].isEnabled()) members[j].setTransferHandler(new ValueExportTransferHandler(Integer.toString(j)));
	
	        members[j].addMouseMotionListener(new MouseAdapter() {
	            @Override
	            public void mouseDragged(MouseEvent e) {
	                JButton button = (JButton) e.getSource();
	                TransferHandler handle = button.getTransferHandler();
	                handle.exportAsDrag(button, e, TransferHandler.COPY);
	            }
	        });
	        
	        if(markets[j].isEnabled()){markets[j].setTransferHandler(new ValueImportTransferHandler());}
        }
        
        if(production[0].isEnabled()){production[0].setTransferHandler(new ValueImportTransferHandler());}
        if(productions[0].isEnabled()){productions[0].setTransferHandler(new ValueImportTransferHandler());}
        if(harvest[0].isEnabled()){harvest[0].setTransferHandler(new ValueImportTransferHandler());}
        if(harvests[0].isEnabled()){harvests[0].setTransferHandler(new ValueImportTransferHandler());}
        if(council[0].isEnabled()){council[0].setTransferHandler(new ValueImportTransferHandler());}
        
        for(int j=0; j<16; j++){
        	if(placements[j].isEnabled()){placements[j].setTransferHandler(new ValueImportTransferHandler());}
        }

        
        java.util.Timer timer = new java.util.Timer();
        timer.schedule(new Repeat(harvest,harvests,1), 0, 2000);
        timer.schedule(new Repeat(production,productions,2), 0, 2000);
        timer.schedule(new Repeat(council,councils,3), 0, 2000);
       
        Arrays.fill(leaderHand, true);
        Arrays.fill(leaderTable, false);
        Arrays.fill(leaderPlayed, false);
        
        //Componenti per la chat
        
        chat.setLocation((int)(screenSize.getWidth()*84/100),(int)(screenSize.getHeight()*97/100));
        chat.setSize((int)(screenSize.getWidth()*15/100),(int)(screenSize.getHeight()*3/100));
        chat.setContentAreaFilled(false);
        chat.setFocusPainted(false);
        chat.setOpaque(true);
        chat.setFont(fontSMALL);
        chat.setBackground(new Color(78,52,46));
        chat.setForeground(Color.WHITE);
        chat.setVisible(true);
        
        send.setLocation((int)(screenSize.getWidth()*95/100),(int)(screenSize.getHeight()*97/100));
        send.setSize((int)(screenSize.getWidth()*4/100),(int)(screenSize.getHeight()*3/100));
        send.setContentAreaFilled(false);
        send.setFocusPainted(false);
        send.setOpaque(true);
        send.setDisabledIcon( send.getIcon() );
        send.setBackground(new Color(78,52,46));
        send.setForeground(Color.BLUE);
        send.setVisible(false);
        
        message.setLocation((int)(screenSize.getWidth()*84/100),(int)(screenSize.getHeight()*97/100));
        message.setSize((int)(screenSize.getWidth()*11/100),(int)(screenSize.getHeight()*3/100));
        message.setEditable(true);
        message.setForeground(Color.BLACK);
        message.setFont(extraSmall);
        message.setBackground(new Color(235,235,235));
        message.setVisible(false);
        
        chatBox.setLocation((int)(screenSize.getWidth()*84/100),(int)(screenSize.getHeight()*73/100));
        chatBox.setSize((int)(screenSize.getWidth()*15/100),(int)(screenSize.getHeight()*24/100));
        chatBox.setEditable(true);
        chatBox.setForeground(Color.BLACK);
        chatBox.setFont(extraSmall);
        chatBox.setBackground(new Color(235,235,235));
        chatBox.setVisible(false);

        
        chat.addMouseListener(new MouseAdapter()
       	{        		
       		public void mousePressed(MouseEvent evt)
	        {
       			
       			MediaPlayer mediaPlayer10 = new MediaPlayer(sendA);
				mediaPlayer10.play();
       			
       			if(chatOpen==false){
       				chat.setLocation((int)(screenSize.getWidth()*84/100),(int)(screenSize.getHeight()*70/100));
       				send.setVisible(true);
       				message.setVisible(true);
       				chatBox.setVisible(true);
       				chat.setText("Chat");
       				
       				chatOpen=true;
       			}
       			
       			else{
       				chat.setLocation((int)(screenSize.getWidth()*84/100),(int)(screenSize.getHeight()*97/100));
       				send.setVisible(false);
       				message.setVisible(false);
       				chatBox.setVisible(false);
       				chat.setText("Chat");
       				chatOpen=false;
       			}
       			
            }
    	});
        
        send.addMouseListener(new MouseAdapter()
       	{        		
       		public void mousePressed(MouseEvent evt)
	        {
       			MediaPlayer mediaPlayer11 = new MediaPlayer(sendA);
				mediaPlayer11.play();
       			
       			sendChatText(message.getText());
       			message.setText("");
	        }
    	});
	        
        
        
        //KeyBinding per tasto ESC
        Action esc = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

            	MediaPlayer mediaPlayer4 = new MediaPlayer(switchSound);
				mediaPlayer4.play();
            	escFrame.setVisible(true);}};
        
            	
        Action escClose = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

            	MediaPlayer mediaPlayer5 = new MediaPlayer(switchSound);
				mediaPlayer5.play();
            	escFrame.dispose();}};
            	
        KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0); 
        
        JPanel towerPanel = (JPanel) towers.getContentPane();
        InputMap inputMap = towerPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(stroke, "OPEN");
        towerPanel.getActionMap().put("OPEN", esc);    
        
        JPanel otherPanel = (JPanel) others.getContentPane();
        InputMap inputMap2 = otherPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap2.put(stroke, "OPEN");
        otherPanel.getActionMap().put("OPEN", esc);
        
        JPanel pbPanel = (JPanel) personalBoard.getContentPane();
        InputMap inputMap4 = pbPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap4.put(stroke, "OPEN");
        pbPanel.getActionMap().put("OPEN", esc);
        
        JPanel escPanel = (JPanel) escFrame.getContentPane();
        InputMap inputMap3 = escPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap3.put(stroke, "OPEN");
        escPanel.getActionMap().put("OPEN", escClose); 
        
        JPanel desktopPanel = (JPanel) desktopFrame.getContentPane();
        InputMap inputMap5 = desktopPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap5.put(stroke, "OPEN");
        desktopPanel.getActionMap().put("OPEN", esc);
        
        //setBoardPlayers();
        
        //Inizializzazione dei Frame 
        escFrame.getContentPane().add(board3Label);
    	escFrame.setUndecorated(true);
   	 	escFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
   	 	escFrame.setSize(escWidth, escHeight);
   	 	escFrame.setResizable(false);
   	 	escFrame.setLocationRelativeTo(null);        

   	 	towers.add(scrollTowers);
   	 	towers.getContentPane().add(board2Label);
   	 	towers.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   	 	towers.setSize(width, height);
   	 	
   		others.add(scrollOthers);
   	 	others.getContentPane().add(board1Label);
   	 	others.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   	 	others.setSize(width, height);
   	 	
        towers.setResizable(false);
        towers.setVisible(true); 
        
        others.setResizable(false);
        others.setVisible(true); 
        
        personalBoard.setResizable(false);
        personalBoard.setVisible(true); 
         
        desktopFrame.add(chat);
		desktopFrame.add(send);
		desktopFrame.add(message);
		desktopFrame.add(chatBox);
		
		for(int j=0; j<leaders.length;j++){ 
        	desktop.add(leaders[j]); 
        	leaders[j].addMouseListener(new PopClickListener(j));
        }
        
        desktop.add(servants);
        desktop.add(servantsCount);
        
        desktop.add(actionsLog);
        desktop.add(others);
        desktop.add(towers);
	    desktop.setVisible(true);
	    
		setRound();
	    
		desktopFrame.add(pass);
	    desktopFrame.add(timerInfo);
	    desktopFrame.add(resourcesInfo);
	    desktopFrame.add(playerInfo);
	    desktopFrame.add(roundInfo);
	    desktopFrame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
	    desktopFrame.getRootPane().putClientProperty("apple.awt.fullscreenable", Boolean.valueOf(true));
	    desktopFrame.add(desktop);

	    desktopFrame.setLocationRelativeTo(null);
	    desktopFrame.setUndecorated(true);
	    desktopFrame.setVisible(true);
	    
	    enableOSXFullscreen(desktopFrame);
		requestOSXFullscreen(desktopFrame);

		desktopFrame.setSize((int)screenSize.getWidth(), (int)screenSize.getHeight());
		
	    centerJIF(others);
	    centerJIF(towers);
	    
	    scrollTowers.setEnabled(false);
	    scrollOthers.setEnabled(false);
	    
	    new java.util.Timer().schedule( 
    	        new java.util.TimerTask() {
    	            @Override
    	            public void run() {
    	            	scrollTowers.setEnabled(true);
                		scrollOthers.setEnabled(true);
    	            }}, 4000 );
	    
		Animations.startAnimation(towers, others, Direction.RIGHT,towers.getX(),towers.getY() );
	    
	    try {
		    UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		 } catch (Exception e) { e.printStackTrace();}

	    hasLoaded();
	}

	/**
	 * Metodo per centrare un JInternalFrame generico
	 * 
	 * @param jif
	 */
	private void centerJIF(JInternalFrame jif) {
	    
		Dimension desktopSize = desktopFrame.getSize();
	    Dimension jInternalFrameSize = jif.getSize();
	    int width = (desktopSize.width - jInternalFrameSize.width) / 2;
	    int height=desktopSize.height*12/100;
	    jif.setLocation(width, height);
	    jif.setVisible(true);
	    
	}
	
	private int transfer(JButton[] btns, JButton[] lbs, int currentIndex){
		
		if(btns[0].isEnabled()){
		
			if(btns[0].getIcon()!=null)
	        	{
					lbs[currentIndex].setIcon(btns[0].getIcon());
	        		btns[0].setIcon(null);
	        		lbs[currentIndex].setEnabled(true);
	        		currentIndex++;
	        	}
		}
			return currentIndex;
		
	}
	
	private JButton[] initializeButtons(JButton...btns){
		
		for (int j=0;j<btns.length;j++) {
	        btns[j]=new JButton();
	    }
		return btns;
	}
	
	private JTextField[] initialize(JTextField...btns){
		
		for (int j=0;j<btns.length;j++) {
	        btns[j]=new JTextField();
	    }
		return btns;
	}

	
	private JButton[] setLabels(JButton[] btns){
		
		for (JButton btn : btns) {
			
			btn.setOpaque(false);
			btn.setContentAreaFilled(false);
			btn.setFocusPainted(false);
			btn.setEnabled(false);
	        btn.setBorderPainted(false);
		}
		
		return btns;
	}
	
	private JButton[] set(JButton[] btns){
		
		for (JButton btn : btns) {
			
			btn.setOpaque(false);
			btn.setContentAreaFilled(false);
			btn.setFocusPainted(false);
	        //btn.setBorderPainted(false);
		}
		
		return btns;
	}
	
		
	private JButton[] locateCards(JButton[] btns){
		double x=3.2;
		double y;
		int i=0;
		
		//Ciclo 16 volte diviso per colonne ogni colonna 4 posti
		for(int j=0;j<=3;j++){
			y=77;
			for(int z=0;z<=3;z++){
				
				btns[i].setLocation((int)(width*x/100),(int)(height*y/100));
				btns[i].setSize(width*12/100,(int)(height*21.7/100));
				y=y-24;
				i++;
			}	
			x=x+21.9;		
		}
		return btns;
	}
	
	private JButton[] locatePlacements(JButton[] btns){
		double x=15.7;
		double y;
		int i=0;
		
		//Ciclo 16 volte diviso per colonne ogni colonna 4 posti
		for(int j=0;j<=3;j++){
			y=86.6;
			for(int z=0;z<=3;z++){
				
				btns[i].setLocation((int)(width*x/100),(int)(height*y/100));
				btns[i].setSize((int)(width*7.9/100),height*9/100);
				y=y-24.8;
				i++;
			}	
			x=x+21.9;		
		}
		return btns;
	}
	
	
	private JButton[] locateMembers(JButton[] btns){
		double x=7;
		double y=105;
		
		for(int j=0;j<2;j++){
			btns[j].setLocation((int)(width*x/100),(int)(height*y/100));
			btns[j].setSize((int)width*6/100,(int)width*6/100);
			x=x+8;		
		}
		
		x=7;
		y=115;
		
		for(int j=2;j<4;j++){
			btns[j].setLocation((int)(width*x/100),(int)(height*y/100));
			btns[j].setSize((int)width*6/100,(int)width*6/100);
			x=x+8;		
		}
		return btns;
	}
	
	private JButton[] locateExcommStones(JButton[] btns){
		double x=19.4;
		double y=21.8;
		int i=0;
		
		for(int z=0;z<3;z++){
		if(z==1){y=y+1.5;}
			for(int j=0;j<2;j++){
				btns[i].setLocation((int)(width*x/100),(int)(height*y/100));
				btns[i].setSize(width*2/100,height*2/100);
				i=i+1;
				x=x+2.25;		
			}
			
			x=x-4.5;
			y=y+2.4;
			
			for(int j=2;j<4;j++){
				btns[i].setLocation((int)(width*x/100),(int)(height*y/100));
				btns[i].setSize(width*2/100,height*2/100);
				i=i+1;
				x=x+2.25;		
			}
			if(z==1){y=y-1.5;}
			x=x+4.17;
			y=y-2.4;
		}
		
		return btns;
	}
	
	private JButton[] locateCouncils(JButton[] tfs){
		double x=49;
		double y=7;
		int j;
		
		for(j=0;j<tfs.length/2;j++){
			tfs[j].setLocation((int)(width*x/100),(int)(height*y/100));
			tfs[j].setSize(width*6/100,height*7/100);
			x=x+4;		
		}
		
		x=49;
		y=14;
		
		for(j=tfs.length/2; j<tfs.length;j++){
			tfs[j].setLocation((int)(width*x/100),(int)(height*y/100));
			tfs[j].setSize(width*6/100,height*7/100);
			x=x+4;	
		}
		return tfs;
	}
	
	private JButton[] locateDices(JButton[] tfs){
		double x=55.2;
		double y=84.6;
		
		for(int j=0;j<tfs.length;j++){
			tfs[j].setLocation((int)(width*x/100),(int)(height*y/100));
			tfs[j].setSize(width*8/100,height*10/100);
			x=x+10.6;		
		}
		return tfs;
	}
	
	private JButton[] locateLeaders(JButton[] btns){
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		double screenWidth = screenSize.getWidth();
		double screenHeight = screenSize.getHeight();
		
		double y=7.5;
		double x=86;
		
		for(int j=0;j<4;j++){
			btns[j].setLocation((int)(screenWidth*x/100),(int)(screenHeight*y/100));
			btns[j].setSize((int)(screenWidth*8.2/100),(int)(screenHeight*12.05*ratio/100));
			y=y+12.45*ratio;
		}
		
		return btns;
	}
	
	
	
	private JButton[] locateOrders(JButton[] tfs){
		double x=84.1;
		double y=0.5;
		
		for(int j=0;j<tfs.length;j++){
			tfs[j].setLocation((int)(width*x/100),(int)(height*y/100));
			tfs[j].setSize(width*6/100,(int)(height*8.1/100));
			y=y+8.1;		
		}
		return tfs;
	}
	
	private JButton[] locateProductions(JButton[] btns){
		boolean first=true;
		double x=15;
		double y=65.8;
		
		if(first){
			btns[0].setLocation((int)(width*3.5/100),(int)(height*65/100));
			btns[0].setSize(width*7/100,height*9/100);
			first=false;
		}
		
		for(int j=1;j<btns.length;j++){
			btns[j].setLocation((int)(width*x/100),(int)(height*y/100));
			btns[j].setSize(width*5/100,height*7/100);
			btns[j].setEnabled(false);
			btns[j].setBorderPainted(false);
			x=x+3;		
		}
		return btns;
	}
	
	private JButton[] locateHarvests(JButton[] btns){
		boolean first=true;
		double x=15;
		double y=84;
		
		if(first){
			btns[0].setLocation((int)(width*3.5/100),(int)(height*83/100));
			btns[0].setSize(width*7/100,height*9/100);
			first=false;
		}
		
		for(int j=1;j<btns.length;j++){
			btns[j].setLocation((int)(width*x/100),(int)(height*y/100));
			btns[j].setSize(width*5/100,height*7/100);
			btns[j].setEnabled(false);
			btns[j].setBorderPainted(false);
			x=x+3;		
		}
		return btns;
	}
	
	private JButton[] locatePrivileges(JButton[] btns){
		double x=58.5;
		double y=25;
		
		for(int j=0;j<btns.length;j++){
			btns[j].setLocation((int)(width*x/100),(int)(height*y/100));
			btns[j].setSize((int)(width*3/100),(int)(height*4.5/100));
			x=x+3.25;		
		}
		return btns;		
	}
	
	private JButton[] locatePlayers(JButton[] btns){
		double x=7;
		double y=14;
		
		
		for(int j=0;j<btns.length;j++){
			btns[j].setLocation((int)(width*x/100),(int)(height*y/100));
			btns[j].setSize(width*7/100,height*9/100);
			y=y+16;		
		}
		return btns;
	}
	
	private JTextField[] locatePlayersCheck(JTextField[] btns){
		double x=0;
		double y=17;
		
		for(int j=0;j<btns.length;j++){
			btns[j].setLocation((int)(width*x/100),(int)(height*y/100));
			btns[j].setSize(width*7/100,height*3/100);
			y=y+16;		
		}
		return btns;
	}
	
	private JButton[] locateExcommunications(JButton[] tfs){
		
		double x=17.3;
		double y=16;
		
		
		for(int j=0;j<tfs.length;j++){
			tfs[j].setLocation((int)(width*x/100),(int)(height*y/100));
			tfs[j].setSize((int)(width*8.6/100),height*25/100);
			x=x+8.6;		
		}
		y=y+2;
		x=25.9;
		tfs[1].setLocation((int)(width*x/100),(int)(height*y/100));
		return tfs;
	}
	
	
	private JTextField[] locatePlayersInfo(JTextField[] tfs){
		double x=0.5;
		double y=24;
		
		
		for(int j=0;j<tfs.length;j++){
			tfs[j].setLocation((int)(width*x/100),(int)(height*y/100));
			tfs[j].setSize(width*20/100,height*4/100);
			y=y+16;	
		}
		return tfs;
	}
	
	
	private JButton[] fillButtons(JButton[] btns, JLabel[] lbs){
		
		for (int j=0;j<btns.length;j++) {
			btns[j].setIcon(lbs[j].getIcon());
		}
		
		return btns;	
	}
	
	private JButton[] fillLabels(JButton[] btns, JLabel[] lbs){
		
		for (int j=0;j<btns.length;j++) {
			
			btns[j].setIcon(lbs[j].getIcon());
			btns[j].setDisabledIcon( btns[j].getIcon() );
		}
		
		return btns;
	}
	
	private JButton[] fillLeaders(JButton[] btns, JLabel[] lbs, JLabel[] lbs2){
		
		for (int j=0;j<btns.length;j++) {
			
			btns[j].setIcon(lbs[j].getIcon());
			btns[j].setDisabledIcon( lbs2[j].getIcon() );
		}
		
		return btns;
	}
	
	private JButton[] fillCards(JButton[] btns) throws IOException{
		
		for (int j=0;j<btns.length;j++) 
		{
			if (cardsCodes[j] == -1)
				btns[j].setIcon(null);
			else {
				btns[j].setIcon((ImageHandler.setImage("resources/cards/devcards_f_en_c_"+(cardsCodes[j])+".png",14,(int)( 14.5 * ratio * (1.77 /ratio) ),width,height)).getIcon());
				btns[j].setDisabledIcon( btns[j].getIcon());
			}
		}
		
		return btns;
	}
	
	private void enable(JButton...btns){
		for (JButton btn : btns) {
	        btn.setEnabled(true);
	    }
	}
	
	private void disable(JButton...btns){
		for (JButton btn : btns) {
	        btn.setEnabled(false);
	    }
	}
	

	public static class ValueExportTransferHandler extends TransferHandler {
	
	    public static final DataFlavor SUPPORTED_DATE_FLAVOR = DataFlavor.stringFlavor;
	    private String value;
	
	    public ValueExportTransferHandler(String value) {
	        this.value = value;
	    }
	
	    public String getValue() {
	        return value;
	    }
	
	    @Override
	    public int getSourceActions(JComponent c) {
	        return DnDConstants.ACTION_COPY_OR_MOVE;
	    }
	
	    @Override
	    protected Transferable createTransferable(JComponent c) {
	        Transferable t = new StringSelection(getValue());
	        return t;
	    }
	
	    @Override
	    protected void exportDone(JComponent source, Transferable data, int action) {
	        super.exportDone(source, data, action);
	    }
	
	}

	public class ValueImportTransferHandler extends TransferHandler {
	
	    public final DataFlavor SUPPORTED_DATE_FLAVOR = DataFlavor.stringFlavor;
	
	    public ValueImportTransferHandler() {
	    }
	
	    @Override
	    public boolean canImport(TransferHandler.TransferSupport support) {
	        return support.isDataFlavorSupported(SUPPORTED_DATE_FLAVOR);
	    }
	
	    @Override
	    public boolean importData(TransferHandler.TransferSupport support) {
	        boolean accept = false;
	        if (canImport(support)) {
	            try {
	                Transferable t = support.getTransferable();
	                Object value = t.getTransferData(SUPPORTED_DATE_FLAVOR);
	                if (value instanceof String) {
	                	
	                    Component component = support.getComponent();
	                                 
	                    if (component instanceof JButton && component.isEnabled() && ((JButton) component).getIcon()==null && members[Integer.parseInt(value.toString())].isEnabled()) {
	                        
	                    	if(component == markets[0]) {
	                    		MediaPlayer mediaPlayer1 = new MediaPlayer(goldS);
		                		mediaPlayer1.play();
	                    	}
	                    	
	                    	if(component == council[0]){
	                    			activateCouncil(identifySpot(component),Integer.parseInt(value.toString()),servantsCountNumber);
	                    		}
	                    	
	                    	else {
	                    		
		                    		if(component == markets[3]){
		                    			int[] privilegesIndex = new int[2];
		                    			activateCouncilMultiple(identifySpot(component),Integer.parseInt(value.toString()),servantsCountNumber,privilegesIndex);
		                    		}
		                    	
		                    		else {
		                    			notifyAction(identifySpot(component),Integer.parseInt(value.toString()),servantsCountNumber);
		                    		}
	                    	}
	                    	
		                    servantsCountNumber=0;
		                    servantsCount.setText(""+servantsCountNumber);
		                    usedMember= Integer.parseInt(value.toString());
		                    
	                        accept = true;
	                    }
	                    else{
	                    	MediaPlayer mediaPlayer1 = new MediaPlayer(behaviourA);
	                		mediaPlayer1.play();
	                    }
	                }
	            } catch (Exception exp) {
	                exp.printStackTrace();
	            }
	        }
	        return accept;
	    }
	}
	
	class Repeat extends TimerTask {
		JButton big[];
		JButton small[];
		int index;
		
		public Repeat(JButton[] b1, JButton[] b2, int index){
			this.big=b1;
			this.small=b2;
			this.index=index;
		}
		
		public void setLogText(String s){
			actionsLog.setText(s);
		}
		
	    public void run() {
	    	
	    	switch(index){
	    	case 1:
	    		harvestIndex=transfer(big,small,harvestIndex);
	    		break;
	    	case 2:
	    		productionIndex=transfer(big,small,productionIndex);
	    		break;
	    	case 3:
	    		councilIndex=transfer(big,small,councilIndex);
	    		break;
	    	}
	    }
	}
	
	class desktopPopUp extends JPopupMenu {
	    JMenuItem gioca;
	    JMenuItem scarta;
	    JMenuItem attiva;
	    
	    public desktopPopUp(int value, boolean allowed){
	    	
	    	gioca = new JMenuItem("Gioca!");
		        
	    	gioca.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			
	    			notifyLeaderPlacement(value);
	    		}
	    	});
		        
	    		
	    	scarta = new JMenuItem("Scarta!");
		        
		    scarta.addActionListener(new ActionListener() {
		    	public void actionPerformed(ActionEvent e) {
		              
		    		notifyLeaderDiscard(value);	
		        }
		    });
	        

		   attiva = new JMenuItem("Attiva!");
		        
		   attiva.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent e) {
		            
				   notifyLeaderActivation(value);
			   }
		   });
	        
	        if(allowed && leaderTable[value]) add(attiva);
	        if(allowed && leaderHand[value]) add(gioca);
	        if(leaderHand[value]) add(scarta);
	    }
	    
	}
	
	class PopClickListener extends MouseAdapter {
		int value;
		
		public PopClickListener(int value){
			this.value=value;
		}
		
	    public void mousePressed(MouseEvent e){
	        if (e.isPopupTrigger())
	            doPop(e);
	    }

	    public void mouseReleased(MouseEvent e){
	        if (e.isPopupTrigger())
	            doPop(e);
	    }

	    private void doPop(MouseEvent e){
	        desktopPopUp menu = new desktopPopUp(value, allowed);
	        menu.show(e.getComponent(), e.getX(), e.getY());
	    }
	}

	public void startTimer(){
		timer.stop();
		timer = createTimer(1000);
		getTimer().start();
	}
	
    private Timer createTimer(int delay) {
        Timer timer = new Timer(delay, new ActionListener(){
            Time counter = new Time(TimerValue);
            public void actionPerformed(ActionEvent e) {
                if (counter.getTime() == 0) {
                    ((Timer)e.getSource()).stop();
                    if(allowed) notifyTimesUp();
                    
                } else {
                    timerInfo.setText("" + Integer.toString(counter.getTime()));
                    counter.decTime();
                }
            }
        });
        timer.setInitialDelay(0);
        return timer;
    }

    private Timer getTimer() {
        return timer;
    }

    static class Time {
        int time = 1000;
        public Time(int time) {
            this.time = time;
        }
        void decTime() {
            time--;
        }
        int getTime() {
            return time;
        }
    }
	
    
    public static void enableOSXFullscreen(Window window) {
        try {
            Class util = Class.forName("com.apple.eawt.FullScreenUtilities");
            Class params[] = new Class[]{Window.class, Boolean.TYPE};
            Method method = util.getMethod("setWindowCanFullScreen", params);
            method.invoke(util, window, true);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static void requestOSXFullscreen(Window window) {
        try {
            Class appClass = Class.forName("com.apple.eawt.Application");
            Class params[] = new Class[]{};

            Method getApplication = appClass.getMethod("getApplication", params);
            Object application = getApplication.invoke(appClass);
            Method requestToggleFulLScreen = application.getClass().getMethod("requestToggleFullScreen", Window.class);

            requestToggleFulLScreen.invoke(application, window);
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }
    
    public it.polimi.ingsw.ps06.model.Types.Action identifySpot(Component c){
    	
    	if( ( (JButton) c) == council[0] ) return it.polimi.ingsw.ps06.model.Types.Action.COUNCIL_SPACE;
    	if( ( (JButton) c) == harvests[0] ) return it.polimi.ingsw.ps06.model.Types.Action.HARVEST_1;
    	if( ( (JButton) c) == harvest[0] ) return it.polimi.ingsw.ps06.model.Types.Action.HARVEST_2;
    	if( ( (JButton) c) == productions[0] ) return it.polimi.ingsw.ps06.model.Types.Action.PRODUCTION_1;
    	if( ( (JButton) c) == production[0] ) return it.polimi.ingsw.ps06.model.Types.Action.PRODUCTION_2;
    	
    	if( ( (JButton) c) == markets[0] ) return it.polimi.ingsw.ps06.model.Types.Action.MARKET_1;
    	if( ( (JButton) c) == markets[1] ) return it.polimi.ingsw.ps06.model.Types.Action.MARKET_2;
    	if( ( (JButton) c) == markets[2] ) return it.polimi.ingsw.ps06.model.Types.Action.MARKET_3;
    	if( ( (JButton) c) == markets[3] ) return it.polimi.ingsw.ps06.model.Types.Action.MARKET_4;
    	
    	if( ( (JButton) c) == placements[0] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_GREEN_1;
    	if( ( (JButton) c) == placements[1] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_GREEN_2;
    	if( ( (JButton) c) == placements[2] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_GREEN_3;
    	if( ( (JButton) c) == placements[3] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_GREEN_4;
    	
    	if( ( (JButton) c) == placements[4] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_BLUE_1;
    	if( ( (JButton) c) == placements[5] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_BLUE_2;
    	if( ( (JButton) c) == placements[6] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_BLUE_3;
    	if( ( (JButton) c) == placements[7] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_BLUE_4;
    	
    	if( ( (JButton) c) == placements[8] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_YELLOW_1;
    	if( ( (JButton) c) == placements[9] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_YELLOW_2;
    	if( ( (JButton) c) == placements[10] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_YELLOW_3;
    	if( ( (JButton) c) == placements[11] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_YELLOW_4;
    	
    	if( ( (JButton) c) == placements[12] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_PURPLE_1;
    	if( ( (JButton) c) == placements[13] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_PURPLE_2;
    	if( ( (JButton) c) == placements[14] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_PURPLE_3;
    	if( ( (JButton) c) == placements[15] ) return it.polimi.ingsw.ps06.model.Types.Action.TOWER_PURPLE_4;
    	
    	return null;
    	
    }
    
    public JButton identifyComponent(it.polimi.ingsw.ps06.model.Types.Action chosenAction){
    	
    	switch(chosenAction){
    	case COUNCIL_SPACE:
    		return council[0];
    	case HARVEST_1:
    		return harvests[0];
    	case HARVEST_2:
    		return harvest[0];
    	case PRODUCTION_1:
    		return productions[0];
    	case PRODUCTION_2:
    		return production[0];
    		
    	case MARKET_1:
    		return markets[0];
    	case MARKET_2:
    		return markets[1];
    	case MARKET_3:
    		return markets[2];
    	case MARKET_4:
    		return markets[3];
    		
    	case TOWER_GREEN_1:
    		return placements[0];
    	case TOWER_GREEN_2:
    		return placements[1];
    	case TOWER_GREEN_3:
    		return placements[2];
    	case TOWER_GREEN_4:
    		return placements[3];
    		
    	case TOWER_BLUE_1:
    		return placements[4];
    	case TOWER_BLUE_2:
    		return placements[5];
    	case TOWER_BLUE_3:
    		return placements[6];
    	case TOWER_BLUE_4:
    		return placements[7];
    		
    	case TOWER_YELLOW_1:
    		return placements[8];
    	case TOWER_YELLOW_2:
    		return placements[9];
    	case TOWER_YELLOW_3:
    		return placements[10];
    	case TOWER_YELLOW_4:
    		return placements[11];

    	case TOWER_PURPLE_1:
    		return placements[12];
    	case TOWER_PURPLE_2:
    		return placements[13];
    	case TOWER_PURPLE_3:
    		return placements[14];
    	case TOWER_PURPLE_4:
    		return placements[15];
    		
    	default:
    		return null;
    		
    		
    	}
    }
    
	@Override
	public void setHarvestIndex(int value) {
		this.harvestIndex=value;
		
	}


	@Override
	public void setProductionIndex(int value) {
		this.productionIndex=value;
		
	}


	@Override
	public void setCouncilIndex(int value) {
		this.councilIndex=value;
		
	}


	@Override
	public void setPeriodRound(int period, int round) {
		
		roundInfo.setText("Turno: "+round+"  Periodo: "+period);
		
		councilIndex=0;
		harvestIndex=1;
		productionIndex=1;
		
		setRound();
		
	}

	

	@Override
	public void setPlayerNumber(int number) {
		this.playerNumber=number;
		
		setBoardPlayers();
	}


	@Override
	public void setPlayerColor(String s) {
		this.playerColor=s;
		
	}


	@Override
	public void setPlayersNames(String s, int index) {
		playersName[index]=s;
		playersInfo[index].setText(s);
		
		for(int j=0; j<players.length;j++){ if(! (players[j].isVisible())) players[j].setVisible(true); }
		
	}


	@Override
	public void setCurrentPlayerID(int id) {
		
		for(int j=0; j< playersCheck.length; j++){
			playersCheck[j].setText("");
		}
		
		switch(id){
		case 0:
			playersCheck[0].setForeground(new Color(216,35,35));
			playersCheck[0].setText("-->");
			break;
		case 1:
			playersCheck[1].setForeground(new Color(26,211,17));
			playersCheck[1].setText("-->");
			break;
		case 2:
			playersCheck[2].setForeground(new Color(25,153,227));
			playersCheck[2].setText("-->");
			break;
		case 3:
			playersCheck[3].setForeground(new Color(255,248,48));
			playersCheck[3].setText("-->");
			break;
		}
		
		playerInfo.setText("Turno del giocatore: "+playersName[id]);
		
		if(id != playerID){
			blockedStatus();
			pass.setEnabled(false);
		}
		
		if(id == playerID){
			allowedStatus();
			pass.setEnabled(true);
		}
		
	
		startTimer();
	}



	@Override
	public void setDices(int black, int white, int orange) {
		this.blackValue=black;
		this.whiteValue=white;
		this.orangeValue=orange;
		
		try {
			refresh();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void setTimer(int seconds){
		
		this.TimerValue=seconds;
		
		startTimer();
	}


	@Override
	public void setExcommunicationTiles(int code1, int code2, int code3) {
		this.ex1=code1;
		this.ex2=code2;
		this.ex3=code3;
		
		try {
			refresh();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void activateLeaders() {
		leaders = fillLeaders(leaders,leadersLabel,leadersLabelFade);
	}


	@Override
	public void setLeaders(int code1, int code2, int code3, int code4){
		this.lead1=code1;
		this.lead2=code2;
		this.lead3=code3;
		this.lead4=code4;
		
		try {
			refresh();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void setPersonalResources(int coin, int wood, int stone, int servant, int victory, int military, int faith) {
		
		this.coinV=coin;
		this.woodV=wood;
		this.stoneV=stone;
		this.servantV=servant;
		this.victoryV=victory;
		this.militaryV=military;
		this.faithV=faith;
		
		resourcesInfo.setText(coinV+" Coin   "+woodV+" Wood   "+stoneV+" Stone   "+servantV+" Servant     |    " +victoryV+" Victory   "+militaryV+" Military   "+faithV+" Faith");
	}

	

	@Override
	public void addMember(it.polimi.ingsw.ps06.model.Types.Action chosenAction, ColorPalette color, int playerIndex) throws IOException {
		
		String pColor="";
		String fullPColor="";
		String mColor="";
		String fullMColor="";
		
		switch(color){
		case DICE_BLACK:
			mColor="N";
			fullMColor="Nero";
			break;
		case DICE_WHITE:
			mColor="B";
			fullMColor="Bianco";
			break;
		case DICE_ORANGE:
			mColor="A";
			fullMColor="Arancione";
			break;
		case UNCOLORED:
			mColor="E";
			fullMColor="Neutro";
			break;
		default:
			break;
		}
		
		switch(playerIndex){
		case 0:
			pColor="R";
			fullPColor="Rosso";
			break;
		case 1:
			pColor="V";
			fullPColor="Verde";
			break;
		case 2:
			pColor="B";
			fullPColor="Blu";
			break;
		case 3:
			pColor="G";
			fullPColor="Giallo";
			break;
		default:
			break;
		}
		
		JButton btn = identifyComponent(chosenAction);
		
		btn.setIcon((ImageHandler.setImage("resources/member/"+pColor+mColor+".png",5,7,width,height)).getIcon());
    	
        
		MediaPlayer mediaPlayer1 = new MediaPlayer(hit);
		mediaPlayer1.play();
		
		if(playerIndex==playerID){
			member[usedMember] = false;
			members[usedMember].setEnabled(false);
			
		}
		
		actionsLog.setText("Il giocatore "+fullPColor+" ha piazzato il familiare "+fullMColor+"!");
	}
	
	@Override
	public void notifyExit() {
		setChanged();
		EventClose close = new EventClose();
		notifyObservers(close);
		
	}


	@Override
	public void notifyAction(it.polimi.ingsw.ps06.model.Types.Action chosenAction, int memberIndex, int servants) {
		
		setChanged();
		EventMemberPlaced memberPlaced;
		
		switch(memberIndex){
		case 0:
			memberPlaced = new EventMemberPlaced(chosenAction, ColorPalette.DICE_BLACK, servants);
			break;
		case 1:
			memberPlaced = new EventMemberPlaced(chosenAction, ColorPalette.DICE_WHITE, servants);
			break;
		case 2:
			memberPlaced = new EventMemberPlaced(chosenAction, ColorPalette.DICE_ORANGE, servants);
			break;
		case 3:
			memberPlaced = new EventMemberPlaced(chosenAction, ColorPalette.UNCOLORED, servants);
			break;
		default:
			memberPlaced=null;
		}
		
		notifyObservers(memberPlaced);
		
	}


	@Override
	public void notifyLeaderDiscard(int index) {
		setChanged();
		EventLeaderDiscarded leaderDiscarded;
		leaderDiscarded = new EventLeaderDiscarded(index);
		notifyObservers(leaderDiscarded);
		
	}

	

	@Override
	public void notifyLeaderActivation(int index) {
		setChanged();
		EventLeaderActivated leaderActivated;
		leaderActivated = new EventLeaderActivated(index);
		notifyObservers(leaderActivated);
		
	}


	@Override
	public void notifyLeaderPlacement(int index) {
		setChanged();
		EventLeaderPlayed leaderPlayed;
		leaderPlayed = new EventLeaderPlayed(index);
		notifyObservers(leaderPlayed);
		
	}

	
	@Override
	public void addNewObserver(Observer o) {
		addObserver(o);
		
	}


	@Override
	public void notifyTimesUp() {
		
		timerInfo.setText("Times up!");
		
		setChanged();
		MessagePlayerPassed playerPass = new MessagePlayerPassed();
		notifyObservers(playerPass);
		
	}

	
	
	@Override
	public void showPersonalView(int index) {
		this.pvIndex=index;
		
		view = new PersonalViewGUI(pvIndex, this);
		
		try { view.show(); } catch (IOException e) {e.printStackTrace();}

	}


	@Override
	public void setCards(int[] cards) {
		this.cardsCodes = cards;
		
		try {
			refresh();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public void setOwnerPlayerIndex(int index) {
		playerID=index;
		
		switch (index){
		case 0:
			playerColor="R";
			break;
		case 1:
			playerColor="V";
			break;
		case 2:
			playerColor="B";
			break;
		case 3:
			playerColor="G";
			break;
		}
		
		try {
			refresh();
		} catch (IOException e) {
			e.printStackTrace();}
		
	}

	public void refresh() throws IOException{
		getResources();
		
        members = fillButtons(members,membersLabel);
        if(blackValue>=0) dices = fillLabels(dices,dicesLabel);
        if(lead1>=0) leaders = fillLeaders(leaders,leadersLabel,leadersLabelFade);
        if(ex1>=0) excommunications = fillLabels(excommunications, excommunicationsLabel);
        cards = fillCards(cards);
        
	}
	
	public void getResources() throws IOException{

        
		if(blackValue>=0) dicesLabel[0] = ImageHandler.setImage("resources/dice/N"+blackValue+".png",6,8,width,height);
		if(whiteValue>=0) dicesLabel[1] = ImageHandler.setImage("resources/dice/B"+whiteValue+".png",6,8,width,height);
		if(orangeValue>=0) dicesLabel[2] = ImageHandler.setImage("resources/dice/A"+orangeValue+".png",6,8,width,height);
		
        leadersLabel[0] = new JLabel();
        leadersLabel[1] = new JLabel();
        leadersLabel[2] = new JLabel();
        leadersLabel[3] = new JLabel();
        
        if(lead1>=0) leadersLabel[0] = ImageHandler.setImageScreen("resources/leader/leader"+lead1+".jpg",9,(int)(13.23*ratio),width,height);
        if(lead2>=0) leadersLabel[1] = ImageHandler.setImageScreen("resources/leader/leader"+lead2+".jpg",9,(int)(13.23*ratio),width,height);
        if(lead3>=0) leadersLabel[2] = ImageHandler.setImageScreen("resources/leader/leader"+lead3+".jpg",9,(int)(13.23*ratio),width,height);
        if(lead4>=0) leadersLabel[3] = ImageHandler.setImageScreen("resources/leader/leader"+lead4+".jpg",9,(int)(13.23*ratio),width,height);
        
        leadersLabelFade[0] = new JLabel();
        leadersLabelFade[1] = new JLabel();
        leadersLabelFade[2] = new JLabel();
        leadersLabelFade[3] = new JLabel();
        
        if(lead1>=0) leadersLabelFade[0] = ImageHandler.setImageScreen("resources/leader/leader"+lead1+"fade.png",9,(int)(13.23*ratio),width,height);
        if(lead2>=0) leadersLabelFade[1] = ImageHandler.setImageScreen("resources/leader/leader"+lead2+"fade.png",9,(int)(13.23*ratio),width,height);
        if(lead3>=0) leadersLabelFade[2] = ImageHandler.setImageScreen("resources/leader/leader"+lead3+"fade.png",9,(int)(13.23*ratio),width,height);
        if(lead4>=0) leadersLabelFade[3] = ImageHandler.setImageScreen("resources/leader/leader"+lead4+"fade.png",9,(int)(13.23*ratio),width,height);
        
        membersLabel[0] = ImageHandler.setImage("resources/member/"+playerColor+"N.png",5,7,width,height);
        membersLabel[1] = ImageHandler.setImage("resources/member/"+playerColor+"B.png",5,7,width,height);
        membersLabel[2] = ImageHandler.setImage("resources/member/"+playerColor+"A.png",5,7,width,height);
        membersLabel[3] = ImageHandler.setImage("resources/member/"+playerColor+"E.png",5,7,width,height);
        
        if(ex1>=0) excommunicationsLabel[0] = ImageHandler.setImage("resources/excomm/"+ex1+".png",9,23,width,height);
        if(ex2>=0) excommunicationsLabel[1] = ImageHandler.setImage("resources/excomm/"+ex2+".png",9,22,width,height);
        if(ex3>=0) excommunicationsLabel[2] = ImageHandler.setImage("resources/excomm/"+ex3+".png",9,23,width,height);
	}

	private JTextField[] setFont(JTextField[] btns){
		
		for (JTextField btn : btns) {
			btn.setOpaque(false);
			btn.setEditable(false);
			btn.setBorder(null);
			btn.setFont(fontMEDIUM);
			btn.setForeground(Color.BLACK);
			btn.setHorizontalAlignment(JTextField.CENTER);
		}
		
		return btns;
	}
	
	private void setBoardPlayers(){
		
		switch(playerNumber){
		
        case 2:
        	players[2].setEnabled(false);
        	
        	for( MouseListener al : players[2].getMouseListeners() ) {
        	 	players[2].removeMouseListener( al );
        	}
        	
        	harvest[0].setDisabledIcon(harvestCover.getIcon());
        	harvest[0].setIcon(harvestCover.getIcon());
        	harvest[0].setEnabled(false);
        	
        	production[0].setDisabledIcon(productionCover.getIcon());
        	production[0].setIcon(productionCover.getIcon());
        	production[0].setEnabled(false);
        	
        case 3:
        	players[3].setEnabled(false);
        	
        	for( MouseListener al : players[3].getMouseListeners() ) {
        	 	players[3].removeMouseListener( al );
        	}
        	
        	markets[2].setDisabledIcon(marketCover1.getIcon());
        	markets[2].setIcon(marketCover1.getIcon());
        	markets[2].setEnabled(false);
        	
        	markets[3].setDisabledIcon(marketCover2.getIcon());
        	markets[3].setIcon(marketCover2.getIcon());
        	markets[3].setEnabled(false);
       
        case 4:
        	players[4].setEnabled(false);
        	harvest[0].setBorderPainted(false);
        	production[0].setBorderPainted(false);
        	markets[2].setBorderPainted(false);
        	markets[3].setBorderPainted(false);
        	
        	for( MouseListener al : players[4].getMouseListeners() ) {
        	 	players[4].removeMouseListener( al );
        	}
        
        default: 
        	break;
        }
		
	}
	
	private void setBoard(){

        startTimer();
	}
	
	private void blockedStatus(){
		
		
		allowed=false;
		
		for (int j=0; j<members.length ; j++){
			members[j].setEnabled(false);
		}
	}
	
	private void allowedStatus(){
		
		allowed=true;
		
		if(member[0]) members[0].setEnabled(true);
		if(member[1]) members[1].setEnabled(true);
		if(member[2]) members[2].setEnabled(true);
		if(member[3]) members[3].setEnabled(true);
	}
	
	
	@Override
	public void excommunicate(int tileNumber, int playerIndex) {
		
		int excommIndex=0;
		
		if(tileNumber==1) excommIndex=excomm1Count;
		if(tileNumber==2) excommIndex=4+excomm2Count;
		if(tileNumber==3) excommIndex=8+excomm3Count;
		
		String color=null;
		
		if(playerIndex==0) color="red";
		if(playerIndex==1) color="green";
		if(playerIndex==2) color="blue";
		if(playerIndex==3) color="yellow";
		
		try {excommStones[excommIndex].setIcon((ImageHandler.setImage("resources/stone/"+color+".png",3.5,(3.5*(16/9)*ratio),width,height)).getIcon());} catch (IOException e) {}
		excommStones[excommIndex].setDisabledIcon(excommStones[excommIndex].getIcon());
		excommStones[excommIndex].setOpaque(true);
		
		if(tileNumber==1) excomm1Count++;
		if(tileNumber==2) excomm2Count++;
		if(tileNumber==3) excomm3Count++;
		
		MediaPlayer mediaPlayer2 = new MediaPlayer(exc);
		mediaPlayer2.play();
	}

	@Override
	public void setRound() {

		for (int j=0; j<member.length ; j++){
			member[j] = true;
		}
		
		for(int j=0; j<placements.length ; j++){placements[j].setIcon(null);}
		for(int j=0; j<councils.length ; j++){councils[j].setIcon(null);}
		
		council[0].setIcon(null);
		
		for(int j=0; j<markets.length ; j++){
				
				if(markets[j].isEnabled()) markets[j].setIcon(null);
			}
		
		for(int j=0; j<harvests.length ; j++){harvests[j].setIcon(null);}
		for(int j=0; j<productions.length ; j++){productions[j].setIcon(null);}
		
		for(int j=0; j<5; j++){
			
			if(leaderPlayed[j]==true){
				leaderPlayed[j]=false;
				leaderTable[j]=true;
			}
		}
		
		
		try {
			refresh();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void showErrorLog(String s) {
		actionsLog.setText(s);
		
	}

	@Override
	public void hasLoaded() {
		setChanged();
		BoardHasLoaded roomLoaded = new BoardHasLoaded();
		notifyObservers(roomLoaded);
	}
	
	private void activateCouncil(it.polimi.ingsw.ps06.model.Types.Action chosenAction, int memberIndex, int servants){
		
    	for(int j=0; j<privileges.length; j++){
    		privileges[j].setEnabled(true);
    		privileges[j].setBorderPainted(true);
    	}
    	
    	setChanged();
		
       	new java.util.Timer().schedule( 
  	        new java.util.TimerTask() {
  	            @Override
   	            public void run() {
  	            	
  	            	for(int j=0; j<privileges.length; j++){
  	            		privileges[j].setEnabled(false);
  	            		privileges[j].setBorderPainted(false);
  	            		
  	            		ActionListener[] actionListeners = privileges[j].getActionListeners();
  	            		for (ActionListener actionListener : actionListeners) {
  	            			privileges[j].removeActionListener(actionListener);
  	            		}
  	            		
  	            }
  	            	
  	        }}, 10000 );
       	
       	    		
    	privileges[0].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
        	            	      	
               	MediaPlayer mediaPlayer2 = new MediaPlayer(hit2);
        		mediaPlayer2.play();
    	    		
        		for(int j=0; j<privileges.length; j++){
        			
        			privileges[j].setEnabled(false);
                   	privileges[j].setBorderPainted(false);
                   		
                  	ActionListener[] actionListeners = privileges[j].getActionListeners();
                   	for (ActionListener actionListener : actionListeners) {
                   		privileges[j].removeActionListener(actionListener);
                   	}
        		}
            	
        		EventMemberPlacedWithPrivilege memberPlaced = new EventMemberPlacedWithPrivilege(chosenAction, findColor(memberIndex), servants, CouncilPrivilege.getPrivilege(0));
        		notifyObservers(memberPlaced);
    	    }
    	});
    	
    	privileges[1].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
        	            	      	
               	MediaPlayer mediaPlayer2 = new MediaPlayer(hit2);
        		mediaPlayer2.play();
    	    		
        		for(int j=0; j<privileges.length; j++){
        			
        			privileges[j].setEnabled(false);
                   	privileges[j].setBorderPainted(false);
                   		
                  	ActionListener[] actionListeners = privileges[j].getActionListeners();
                   	for (ActionListener actionListener : actionListeners) {
                   		privileges[j].removeActionListener(actionListener);
                   	}
        		}
        			
        		EventMemberPlacedWithPrivilege memberPlaced = new EventMemberPlacedWithPrivilege(chosenAction, findColor(memberIndex), servants, CouncilPrivilege.getPrivilege(1));
        		notifyObservers(memberPlaced);
        		
    	    }
    	});
    	
    	privileges[2].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
        	            	      	
               	MediaPlayer mediaPlayer2 = new MediaPlayer(goldES);
        		mediaPlayer2.play();
    	    		
        		for(int j=0; j<privileges.length; j++){
        			
        			privileges[j].setEnabled(false);
                   	privileges[j].setBorderPainted(false);
                   		
                  	ActionListener[] actionListeners = privileges[j].getActionListeners();
                   	for (ActionListener actionListener : actionListeners) {
                   		privileges[j].removeActionListener(actionListener);
                   	}
        		}
        			
        		EventMemberPlacedWithPrivilege memberPlaced = new EventMemberPlacedWithPrivilege(chosenAction, findColor(memberIndex), servants, CouncilPrivilege.getPrivilege(2));
        		notifyObservers(memberPlaced);
        		
    	    }
    	});
    	
    	privileges[3].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
        	            	      	
               	MediaPlayer mediaPlayer2 = new MediaPlayer(hit2);
        		mediaPlayer2.play();
    	    		
        		for(int j=0; j<privileges.length; j++){
        			
        			privileges[j].setEnabled(false);
                   	privileges[j].setBorderPainted(false);
                   		
                  	ActionListener[] actionListeners = privileges[j].getActionListeners();
                   	for (ActionListener actionListener : actionListeners) {
                   		privileges[j].removeActionListener(actionListener);
                   	}
        		}
        			
        		EventMemberPlacedWithPrivilege memberPlaced = new EventMemberPlacedWithPrivilege(chosenAction, findColor(memberIndex), servants, CouncilPrivilege.getPrivilege(3));
        		notifyObservers(memberPlaced);
        		
    	    }
    	});
    	
    	privileges[4].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
        	            	      	
               	MediaPlayer mediaPlayer2 = new MediaPlayer(hit2);
        		mediaPlayer2.play();
    	    		
        		for(int j=0; j<privileges.length; j++){
        			
        			privileges[j].setEnabled(false);
                   	privileges[j].setBorderPainted(false);
                   		
                  	ActionListener[] actionListeners = privileges[j].getActionListeners();
                   	for (ActionListener actionListener : actionListeners) {
                   		privileges[j].removeActionListener(actionListener);
                   	}
        		}
        			
        		EventMemberPlacedWithPrivilege memberPlaced = new EventMemberPlacedWithPrivilege(chosenAction, findColor(memberIndex), servants, CouncilPrivilege.getPrivilege(4));
        		notifyObservers(memberPlaced);
        		
    	    }
    	});
   	}
	
private void activateCouncilMultiple(it.polimi.ingsw.ps06.model.Types.Action chosenAction, int memberIndex, int servants, int privilegesIndex[]){
	
	if(privilegeCount < privilegesIndex.length){

    	for(int j=0; j<privileges.length; j++){
    		privileges[j].setEnabled(true);
    		privileges[j].setBorderPainted(true);
    	}
    	
    	setChanged();
		
       	new java.util.Timer().schedule( 
  	        new java.util.TimerTask() {
  	            @Override
   	            public void run() {
  	            	
  	            	for(int j=0; j<privileges.length; j++){
  	            		privileges[j].setEnabled(false);
  	            		privileges[j].setBorderPainted(false);
  	            		
  	            		ActionListener[] actionListeners = privileges[j].getActionListeners();
  	            		for (ActionListener actionListener : actionListeners) {
  	            			privileges[j].removeActionListener(actionListener);
  	            		}
  	            		
  	            }
  	            	
  	        }}, 10000 );
       	
       	    		
    	privileges[0].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
        	            	      	
               	MediaPlayer mediaPlayer2 = new MediaPlayer(hit2);
        		mediaPlayer2.play();
    	    		
        		for(int j=0; j<privileges.length; j++){
        			
        			privileges[j].setEnabled(false);
                   	privileges[j].setBorderPainted(false);
                   		
                  	ActionListener[] actionListeners = privileges[j].getActionListeners();
                   	for (ActionListener actionListener : actionListeners) {
                   		privileges[j].removeActionListener(actionListener);
                   	}
        		}
            	
        		privilegesIndex[privilegeCount] = 0;
        		privilegeCount++;
        		activateCouncilMultiple(chosenAction, memberIndex, servants, privilegesIndex);
    	    }
    	});
    	
    	privileges[1].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
        	            	      	
               	MediaPlayer mediaPlayer2 = new MediaPlayer(hit2);
        		mediaPlayer2.play();
    	    		
        		for(int j=0; j<privileges.length; j++){
        			
        			privileges[j].setEnabled(false);
                   	privileges[j].setBorderPainted(false);
                   		
                  	ActionListener[] actionListeners = privileges[j].getActionListeners();
                   	for (ActionListener actionListener : actionListeners) {
                   		privileges[j].removeActionListener(actionListener);
                   	}
        		}
        			
        		privilegesIndex[privilegeCount] = 1;
        		privilegeCount++;
        		activateCouncilMultiple(chosenAction, memberIndex, servants, privilegesIndex);
        		
    	    }
    	});
    	
    	privileges[2].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
        	            	      	
               	MediaPlayer mediaPlayer2 = new MediaPlayer(goldES);
        		mediaPlayer2.play();
    	    		
        		for(int j=0; j<privileges.length; j++){
        			
        			privileges[j].setEnabled(false);
                   	privileges[j].setBorderPainted(false);
                   		
                  	ActionListener[] actionListeners = privileges[j].getActionListeners();
                   	for (ActionListener actionListener : actionListeners) {
                   		privileges[j].removeActionListener(actionListener);
                   	}
        		}
        			
        		privilegesIndex[privilegeCount] = 2;
        		privilegeCount++;
        		activateCouncilMultiple(chosenAction, memberIndex, servants, privilegesIndex);
        		
    	    }
    	});
    	
    	privileges[3].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
        	            	      	
               	MediaPlayer mediaPlayer2 = new MediaPlayer(hit2);
        		mediaPlayer2.play();
    	    		
        		for(int j=0; j<privileges.length; j++){
        			
        			privileges[j].setEnabled(false);
                   	privileges[j].setBorderPainted(false);
                   		
                  	ActionListener[] actionListeners = privileges[j].getActionListeners();
                   	for (ActionListener actionListener : actionListeners) {
                   		privileges[j].removeActionListener(actionListener);
                   	}
        		}
        			
        		privilegesIndex[privilegeCount] = 3;
        		privilegeCount++;
        		activateCouncilMultiple(chosenAction, memberIndex, servants, privilegesIndex);
        		
    	    }
    	});
    	
    	privileges[4].addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
        	            	      	
               	MediaPlayer mediaPlayer2 = new MediaPlayer(hit2);
        		mediaPlayer2.play();
    	    		
        		for(int j=0; j<privileges.length; j++){
        			
        			privileges[j].setEnabled(false);
                   	privileges[j].setBorderPainted(false);
                   		
                  	ActionListener[] actionListeners = privileges[j].getActionListeners();
                   	for (ActionListener actionListener : actionListeners) {
                   		privileges[j].removeActionListener(actionListener);
                   	}
        		}
        			
        		privilegesIndex[privilegeCount] = 4;
        		privilegeCount++;
        		activateCouncilMultiple(chosenAction, memberIndex, servants, privilegesIndex);
        		
    	    }
    	});
		
	}
	else{
		
		privilegeCount=0;
		EventMemberPlacedWithDoublePrivilege memberPlaced = new EventMemberPlacedWithDoublePrivilege(chosenAction, findColor(memberIndex), servants, CouncilPrivilege.getPrivilege(privilegesIndex[0]), CouncilPrivilege.getPrivilege(privilegesIndex[1]) );
		notifyObservers(memberPlaced);
		
		
	}
}
	
	@Override
	public void setOrder(int[] players) {
		int index=0;
		String color=null;
		
		for(int player : players){
			
			switch(player){
			case 0:
				color="red";
				break;
			case 1:
				color="green";
				break;
			case 2:
				color="blue";
				break;
			case 3:
				color="yellow";
				break;
			default:
				break;
				
			}
			
			try {
				orders[index].setIcon((ImageHandler.setImage("resources/piece/"+color+".png",3.5,(3*ratio),width,height)).getIcon());
				orders[index].setDisabledIcon(orders[index].getIcon());
			} catch (IOException e) {e.printStackTrace();}
			
			index++;
		}
		
	}

	private ColorPalette findColor(int memberIndex){
		
		switch(memberIndex){
		case 0:
			return ColorPalette.DICE_BLACK;
		case 1:
			return ColorPalette.DICE_WHITE;
		case 2:
			return ColorPalette.DICE_ORANGE;
		case 3:
			return ColorPalette.UNCOLORED;
		default:
			return null;
		}
		
	}
	
	
	
	@Override
	public void setResourcesPersonalView(int coin, int wood, int stone, int servant, int victory, int military,
			int faith) {
		view.setResources(coin, wood, stone, servant, victory, military, faith);
		
	}

	@Override
	public void setTerritoryCardPersonalView(int id, int index) {
		view.setTerritoryCard(id, index);
	
	}
	

	@Override
	public void setBuildingCardPersonalView(int id, int index) {
		view.setBuildingCard(id, index);
		
	}
	
	@Override
	public void setBonusTilePersonalView(int code) throws IOException{
		view.setTileCode(code);
	}

	@Override
	public void hasLoadedPersonalView() {
		
		setChanged();
		MessageObtainPersonalBoardStatus obtainPbStatus = new MessageObtainPersonalBoardStatus(pvIndex);
		notifyObservers(obtainPbStatus);
	}
	
	@Override
	public void activateLeader(int value) {
		if(leaders[value].isEnabled()){
			
			   leaders[value].setIcon(leaderBack.getIcon());
			   leaders[value].setDisabledIcon(leaderBack.getIcon());
			   leaderPlayed[value]=true;
			   leaderTable[value]=false;
		}
	}
	
	@Override
	public void discardLeader(int value) {
		
		leaders[value].disable();
       	leaders[value].setIcon(null);
       	
       	MouseListener[] mouseListeners = leaders[value].getMouseListeners();
       	for (MouseListener mouseListener : mouseListeners) {
       		leaders[value].removeMouseListener(mouseListener);
       	}
	}
	
	@Override
	public void playLeader(int value) {
		
		leaders[value].setEnabled(true);
		leaderHand[value]=false;
		leaderTable[value]=true;
	}

	public void turnLeader(int value) {
		
	}
	
	@Override
	public void gameHasEnded(int ID){
		JOptionPane.showMessageDialog(null, "Il giocatore "+ID+" ha vinto!" , "Vittoria!",  JOptionPane.INFORMATION_MESSAGE);
		desktopFrame.dispose();
		
	}

	@Override
	public void sendChatText(String s) {
		setChanged();
		MessageTelegram msg = new MessageTelegram(s);
		notifyObservers(msg);
		
	}

	@Override
	public void addChatText(int player, String s) {

		String s1 = "\n"+"["+player+"]: "+s;
		chatBox.setText(chatBox.getText() + s1);
		chat.setText("• Chat  ");
		
		MediaPlayer mediaPlayer12 = new MediaPlayer(messageA);
		mediaPlayer12.play();
		
	}
	
	
	public static void main( String[] args ) throws IOException
    {
        BoardGUI b = new BoardGUI();
        b.show();

    }
	
}
