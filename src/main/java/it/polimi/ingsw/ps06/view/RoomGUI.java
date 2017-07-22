package it.polimi.ingsw.ps06.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import it.polimi.ingsw.ps06.model.events.EventClose;
import it.polimi.ingsw.ps06.model.events.RoomHasLoaded;
import it.polimi.ingsw.ps06.model.events.StoryBoard2Board;
import it.polimi.ingsw.ps06.networking.messages.MessageGameStart;
import it.polimi.ingsw.ps06.networking.messages.MessageUser;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class RoomGUI extends Observable implements Room {
	private JButton exit;
	private JFrame f;
	private JTextField username, stat1, stat2, stat3, stat4, logged;
	private JTextField[] player = new JTextField[5];
	private JPasswordField password;
	private JButton login, start, register;
	private JButton menuSingle, menuMulti, menuLogin, menuStats,menuOption, menuExit, menuBack, menuMute;
	private JTextField option1, option2;
	private JComboBox optionBox1;
	private Font font,font2;
	private int width;
	private int height;
	private boolean mute = false;
	private AudioClip mediaPlayer4;
	private Media hit2,hit, yes,no;

		
	@Override
	public void show() throws IOException
	{
		try {
		    UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		 } catch (Exception e) { e.printStackTrace();}
		
		exit = new JButton();
		f = new JFrame();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		
		double ratio= (screenSize.getWidth()/screenSize.getHeight());
		
		width = (int)((screenSize.getWidth()*70/100)*(0.8547 / ratio));
		height = (int)(screenSize.getHeight()*70/100);
		
		font = new Font("Lucida Handwriting",Font.PLAIN,(int)(14*(screenSize.getHeight()/1080)));
		font2 = new Font("Lucida Handwriting",Font.PLAIN,(int)(18*(screenSize.getHeight()/1080)));
	    
		
		//Caricamento e resize delle immagini
		JLabel stanza = ImageHandler.setImage("resources/newStanza2.png", 100, 100, width, height);
		
        //Caricamento suoni del gioco

		String hoverSound = "/menuhover.mp3";
        String mediaURL = getClass().getResource(hoverSound).toExternalForm();
		hit = new Media(mediaURL);
		
		String selectSound = "/menuselect.mp3";
        String mediaURL2 = getClass().getResource(selectSound).toExternalForm();
		hit2 = new Media(mediaURL2);
		
		String exitSound = "/exit.mp3";
        String mediaURL3 = getClass().getResource(exitSound).toExternalForm();
		Media music1 = new Media(mediaURL3);
		
		String menu = "/music1.mp3";
        String mediaURL4 = getClass().getResource(menu).toExternalForm();
		Media menuMusic = new Media(mediaURL4);
		
		String yesS = "/yes.mp3";
		String mediaURL5 = getClass().getResource(yesS).toExternalForm();
		yes = new Media(mediaURL5);
		
		String noS = "/no.mp3";
		String mediaURL6 = getClass().getResource(noS).toExternalForm();
		no = new Media(mediaURL6);

		
		mediaPlayer4 = new AudioClip(menuMusic.getSource());
		mediaPlayer4.setVolume(0.3);
       	mediaPlayer4.play();
	        
		//Inizializzazione dei componenti
       	
       	menuSingle = new JButton("PLAY OFFLINE");
       	menuSingle.setLocation(width*25/100,height*43/100);
       	setMenuButton(menuSingle,1);
        
        menuMulti = new JButton("PLAY ONLINE");
        menuMulti.setLocation(width*25/100,height*47/100);
        setMenuButton(menuMulti,2);
        
        menuLogin = new JButton("LOGIN");
        menuLogin.setLocation(width*25/100,height*51/100);
        setMenuButton(menuLogin,3);
        
        menuStats = new JButton("STATISTICS");
        menuStats.setLocation(width*25/100,height*55/100);
        setMenuButton(menuStats,4);
        
        menuOption = new JButton("OPTIONS");
        menuOption.setLocation(width*25/100,height*59/100);
        setMenuButton(menuOption,5);
        
        menuExit = new JButton("EXIT");
        menuExit.setLocation(width*25/100,height*63/100);
        setMenuButton(menuExit,6); 
        
        menuBack = new JButton("BACK");
        menuBack.setLocation(width*45/100, height*85/100);
        setMenuButton(menuBack,7);
        
        menuMute = new JButton("MUTE");
        menuMute.setLocation(width*7/100, height*85/100);
        setMenuButton(menuMute,8);
        
        username = new JTextField("Username");
        username.setLocation(width*35/100,(int)(height*52/100));
        username.setSize(width*30/100,(int)(height*4/100));
        username.setOpaque(true);
        username.setBorder(null);
        username.setBackground(Color.WHITE);
        username.setForeground(Color.BLACK);
        username.setFont(font);
        f.add(username);
       
        
        username.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField)e.getComponent();
                source.setText("");
                source.removeFocusListener(this);
            }
        });
        
        password = new JPasswordField("Passowrd");
        password.setLocation(width*35/100,(int)(height*59/100));
        password.setSize(width*30/100,(int)(height*4/100));
        password.setOpaque(true);
        password.setBorder(null);
        password.setBackground(Color.WHITE);
        password.setForeground(Color.BLACK);
        f.add(password);
        
        password.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                JTextField source = (JTextField)e.getComponent();
                source.setText("");
                source.removeFocusListener(this);
            }
        });
        
        option1 = new JTextField("Quali regole utilizziamo?: ");
        option1.setLocation(width*35/100,height*52/100);
        option1.setSize(width*45/100, height*4/100);
        option1.setOpaque(false);
        option1.setEditable(false);
        option1.setBorder(null);
        option1.setForeground(Color.WHITE);
        option1.setFont(font2);
        f.add(option1);
        
        optionBox1 = new JComboBox();
        optionBox1.setLocation(width*55/100,height*58/100);
        optionBox1.setSize(width*30/100, height*4/100);
        optionBox1.addItem("Regole Semplificate");
        optionBox1.addItem("Regole Avanzate");
        optionBox1.setOpaque(false);
        optionBox1.setEditable(false);
        optionBox1.setBorder(null);
        optionBox1.setFont(font);
        f.add(optionBox1);
        
        stat1 = new JTextField("Partite Giocate: ");
        stat1.setLocation(width*35/100,height*48/100);
        stat1.setSize(width*40/100, height*4/100);
        stat1.setOpaque(false);
        stat1.setEditable(false);
        stat1.setBorder(null);
        stat1.setForeground(Color.WHITE);
        stat1.setFont(font2);
        f.add(stat1);
        
        stat2 = new JTextField("Partite Vinte: ");
        stat2.setLocation(width*35/100,height*52/100);
        stat2.setSize(width*40/100, height*4/100);
        stat2.setOpaque(false);
        stat2.setEditable(false);
        stat2.setBorder(null);
        stat2.setForeground(Color.WHITE);
        stat2.setFont(font2);
        f.add(stat2);
        
        stat3 = new JTextField("Secondi Posti: ");
        stat3.setLocation(width*35/100,height*56/100);
        stat3.setSize(width*40/100, height*4/100);
        stat3.setOpaque(false);
        stat3.setEditable(false);
        stat3.setBorder(null);
        stat3.setForeground(Color.WHITE);
        stat3.setFont(font2);
        f.add(stat3);
        
        stat4 = new JTextField("Punteggio Massimo: ");
        stat4.setLocation(width*35/100,height*60/100);
        stat4.setSize(width*40/100, height*4/100);
        stat4.setOpaque(false);
        stat4.setEditable(false);
        stat4.setBorder(null);
        stat4.setForeground(Color.WHITE);
        stat4.setFont(font2);
        f.add(stat4);
        
        logged = new JTextField("Welcome, guest");
        logged.setLocation(width*0/100,height*95/100);
        logged.setSize(width*100/100,height*5/100);
        logged.setOpaque(false);
        logged.setEditable(false);
        logged.setBorder(null);
        logged.setFont(font2);
        logged.setHorizontalAlignment(JTextField.CENTER);
        logged.setForeground(Color.WHITE);
        f.add(logged);
        
        player[0] = new JTextField();
        player[0].setLocation(width*40/100,height*50/100);
        player[0].setSize(width*30/100,height*4/100);
        player[0].setOpaque(false);
        player[0].setEditable(false);
        player[0].setBorder(null);
        player[0].setFont(font2);
        player[0].setForeground(Color.WHITE);
        f.add( player[0]);
        
        player[1] = new JTextField();
        player[1].setLocation(width*40/100,height*54/100);
        player[1].setSize(width*30/100,height*4/100);
        player[1].setOpaque(false);
        player[1].setEditable(false);
        player[1].setBorder(null);
        player[1].setFont(font2);
        player[1].setForeground(Color.WHITE);
        f.add(player[1]);
        
        player[2] = new JTextField();
        player[2].setLocation(width*40/100,height*58/100);
        player[2].setSize(width*30/100,height*4/100);
        player[2].setOpaque(false);
        player[2].setEditable(false);
        player[2].setBorder(null);
        player[2].setFont(font2);
        player[2].setForeground(Color.WHITE);
        f.add(player[2]);
        
        player[3] = new JTextField();
        player[3].setLocation(width*40/100,height*62/100);
        player[3].setSize(width*30/100,height*4/100);
        player[3].setOpaque(false);
        player[3].setEditable(false);
        player[3].setBorder(null);
        player[3].setFont(font2);
        player[3].setForeground(Color.WHITE);
        f.add(player[3]);
        
        player[4] = new JTextField();
        player[4].setLocation(width*40/100,height*66/100);
        player[4].setSize(width*30/100,height*4/100);
        player[4].setOpaque(false);
        player[4].setEditable(false);
        player[4].setBorder(null);
        player[4].setFont(font2);
        player[4].setForeground(Color.WHITE);
        f.add(player[4]);
        
        
        login = new JButton("Login");
        login.setLocation(width*35/100,height*66/100);
        login.setSize(width*11/100,height*5/100);
        login.setOpaque(false);
        login.setContentAreaFilled(false);
        login.setFocusPainted(false);
        login.setMargin(new Insets(0,0,0,5));
        login.setForeground(Color.WHITE);
        login.setFont(font2);
        login.setBorderPainted(false);
        f.add(login);
        setButtonInteraction(login);
        
        register = new JButton("Register");
        register.setLocation(width*49/100,height*66/100);
        register.setSize(width*16/100,height*5/100);
        register.setOpaque(false);
        register.setContentAreaFilled(false);
        register.setFocusPainted(false);
        register.setMargin(new Insets(0,0,0,5));
        register.setForeground(Color.WHITE);
        register.setFont(font2);
        register.setBorderPainted(false);
        f.add(register);
        setButtonInteraction(register);
        
        start = new JButton("Avvia");
        start.setLocation(width*60/100,height*72/100);
        start.setSize(width*15/100,width*6/100);
        start.setOpaque(false);
        start.setContentAreaFilled(false);
        start.setFocusPainted(false);
        start.setBorder(null);
        start.setMargin(new Insets(0,0,0,5));
        start.setForeground(Color.WHITE);
        start.setFont(font2);
        f.add(start);
        start.setEnabled(false);
        
        menuExit.addMouseListener(new MouseAdapter()
        {
            
            public void mousePressed(MouseEvent evt)
            {
        		notifyExit();
                f.dispose();
            }
            
        });
        
        login.addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent evt)
            {
            	MediaPlayer mediaPlayer3 = new MediaPlayer(hit2);
        		mediaPlayer3.play();
        		giveCredentials(username.getText(),String.valueOf(password.getPassword()));
        		
            }
            
        });
        
        f.setUndecorated(true);
        f.getContentPane().setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
        f.setBackground(new Color(1.0f,1.0f,1.0f,0.0f));
        
	    f.getContentPane().add(stanza);
        f.setUndecorated(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.pack();
     
        
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);  

        f.requestFocusInWindow();
        
        clearAll();
        
        hasLoaded();
	}

	private void setMenuButton(JButton btn, int index){
		
		btn.setSize(width*40/100,width*4/100);
		btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setFocusPainted(false);
        btn.setMargin(new Insets(0,0,0,5));
        btn.setForeground(Color.WHITE);
        btn.setFont(font2);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorderPainted(false);
        f.add(btn);
        
        btn.addMouseListener(new MouseAdapter()
        {
        	public void mousePressed(MouseEvent evt)
            {
            	MediaPlayer mediaPlayer3 = new MediaPlayer(hit2);
        		mediaPlayer3.play();
        		transform(index);
            }

        	public void mouseEntered(MouseEvent evt)
            {

            	MediaPlayer mediaPlayer = new MediaPlayer(hit);
				mediaPlayer.play();
				btn.setForeground(new Color(223,201,125));
            }
        	
        	public void mouseExited(MouseEvent evt)
            {
				btn.setForeground(Color.WHITE);
            	
            }
        });
        
	}
	
	private void setButtonInteraction(JButton btn){
		
		btn.addMouseListener(new MouseAdapter()
        {
            
            public void mouseEntered(MouseEvent evt)
            {

            	MediaPlayer mediaPlayer = new MediaPlayer(hit);
				mediaPlayer.play();
				btn.setForeground(new Color(223,201,125));
            }
            
            public void mouseExited(MouseEvent evt)
            {

				btn.setForeground(Color.WHITE);
            	
            }
            
        });
	}
	
	private void transform(int index){
		
		menuSingle.setVisible(false);
		menuMulti.setVisible(false);
		menuStats.setVisible(false);
		menuLogin.setVisible(false);
		menuOption.setVisible(false);
		menuExit.setVisible(false);
		
		switch(index){
		case 1:
			
			break;
		case 2:
			start.setVisible(true);
			for(int j = 0; j<player.length; j++){player[j].setVisible(true);}
			break;
		case 3:
			username.setVisible(true);
			password.setVisible(true);
			register.setVisible(true);
			login.setVisible(true);
			break;
		case 4:
			stat1.setVisible(true);
			stat2.setVisible(true);
			stat3.setVisible(true);
			stat4.setVisible(true);
			break;
		case 5:
			option1.setVisible(true);
			optionBox1.setVisible(true);
			break;
		case 6:
			break;
		case 7:
			clearAll();
			menuSingle.setVisible(true);
			menuMulti.setVisible(true);
			menuStats.setVisible(true);
			menuLogin.setVisible(true);
			menuOption.setVisible(true);
			menuExit.setVisible(true);
			break;
		case 8:
			menuSingle.setVisible(true);
			menuMulti.setVisible(true);
			menuStats.setVisible(true);
			menuLogin.setVisible(true);
			menuOption.setVisible(true);
			menuExit.setVisible(true);
			
			if(mute) {
				mediaPlayer4.play();
				mute=true;
			}
			else {
				mediaPlayer4.stop();
				mute=true;
			}
		default:
			break;
		}
	}
	
	public void clearAll(){
		
		for(int j = 0; j<player.length; j++){player[j].setVisible(false);}
		register.setVisible(false);
		login.setVisible(false);
		stat1.setVisible(false);
		stat2.setVisible(false);
		stat3.setVisible(false);
		stat4.setVisible(false);
		username.setVisible(false);
		password.setVisible(false);
		start.setVisible(false);
		option1.setVisible(false);
		optionBox1.setVisible(false);
	}
	
	@Override
	public void setPlayer(String name, int index) {
		
		player[index].setText(name);
		
		if(index==1 && name!=null){
			MouseListener[] mouseListeners= start.getMouseListeners();
	  		for (MouseListener mouseListener : mouseListeners) {
	  			start.removeMouseListener(mouseListener);
	  		}
		}
		
		if(index==1 && name!=null){
			
			start.setEnabled(true);
			
			start.addMouseListener(new MouseAdapter()
	        {
	            public void mousePressed(MouseEvent evt)
	            {
	            	MediaPlayer mediaPlayer3 = new MediaPlayer(hit2);
	        		mediaPlayer3.play();
	        		startGame();
	        		mediaPlayer4.stop();
					
	            }
	            
	            public void mouseEntered(MouseEvent evt)
	            {

	            	MediaPlayer mediaPlayer = new MediaPlayer(hit);
					mediaPlayer.play();
					start.setForeground(new Color(223,201,125));
	            }
	            
	            public void mouseExited(MouseEvent evt)
	            {

					start.setForeground(Color.WHITE);
	            	
	            }
	            
	        });
	            
			
		}

		if(index==1 && name.equals("")){
			
			start.setEnabled(false);
			
			for( MouseListener al : start.getMouseListeners() ) {
        	 	start.removeMouseListener( al );
        	}
		}
		
		if(index==3 && name!=null){
			start.setEnabled(false);
			
		}
	}

	@Override
	public void giveCredentials(String username, String password) {
		setChanged();
		MessageUser userMessage;
		
		userMessage = new MessageUser(username, password);
		notifyObservers(userMessage);
		
		MediaPlayer mediaPlayer1 = new MediaPlayer(no);
		mediaPlayer1.play();
	}

	@Override
	public void startGame() {
		setChanged();		
		MessageGameStart gameStart;
		gameStart = new MessageGameStart();
		notifyObservers(gameStart);
			
	}
	
	@Override
	public void hasStarted(){
		
		mediaPlayer4.stop();
		f.dispose();
		setChanged();
		StoryBoard2Board storyBoard;
		storyBoard = new StoryBoard2Board(new BoardGUI());
		notifyObservers(storyBoard);
	}
	


	@Override
	public void clearPlayers() {
		
		for(int j=0; j<5; j++){
			player[j].setText("");
		}
		
	}


	@Override
	public void addNewObserver(Observer o) {
		addObserver(o);
		
	}
	

	@Override
	public void notifyExit() {
		setChanged();
		EventClose close = new EventClose();
		notifyObservers(close);
		
	}


	@Override
	public void hasLoaded() {
		setChanged();
		RoomHasLoaded roomLoaded = new RoomHasLoaded();
		notifyObservers(roomLoaded);
	}


	@Override
	public void userHasLoggedIn(String username, int stat1, int stat2, int stat3, int stat4) {
		
		logged.setText("Welcome, "+username);
		this.stat1.setText("Partite Giocate: " +stat1);
		this.stat2.setText("Partite Vinte: " +stat2);
		this.stat3.setText("Secondi Posti: " +stat3);
		this.stat4.setText("Punteggio Massimo: " +stat4);
		
		MediaPlayer mediaPlayer5 = new MediaPlayer(yes);
		mediaPlayer5.play();
		
		transform(7);
	}
	
    public static void main( String[] args ) throws IOException
    {
        RoomGUI room = new RoomGUI();
        room.show();

    }
}
