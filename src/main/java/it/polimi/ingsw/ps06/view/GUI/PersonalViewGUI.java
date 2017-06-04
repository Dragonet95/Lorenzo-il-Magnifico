package it.polimi.ingsw.ps06.view.GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PersonalViewGUI implements PersonalView {
	private int playerCode;
	private int width, height;
	private JFrame f = new JFrame();
	private JButton exit;
	private JTextField coins, woods, stones, servants, victory, military, faith;
	private Font font;
	
	private int code1, code2, code3, code4;
	private int btCode;


		public PersonalViewGUI(int code) throws IOException {
			this.playerCode=code;
			
			setPersonalView();
			
			exit = new JButton();
			f = new JFrame();
			
			Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
			
			double ratio= (screenSize.getWidth()/screenSize.getHeight());
			
			font = new Font("Lucida Handwriting",Font.PLAIN,(int)(18*(screenSize.getHeight()/1080)));
			
			width = (int)((screenSize.getWidth()*77/100)*(1.349 / ratio));
			height = (int)(screenSize.getHeight()*77/100);
			
			BufferedImage image1 = ImageIO.read(new File("resources/personalView.png")); 
			BufferedImage exit1 = ImageIO.read(new File("resources/button.png")); 
			
			BufferedImage stanza1 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			
			
			Graphics g1 = stanza1.createGraphics();
	        g1.drawImage(image1, 0, 0, width, height, null);
	        g1.dispose();
	        
	        String hoverSound = "resources/menuhover.wav";
			Media hit = new Media(new File(hoverSound).toURI().toString());
			
			String selectSound = "resources/menuselect.wav";
			Media hit2 = new Media(new File(selectSound).toURI().toString());
			
			String exitSound = "resources/exit.wav";
			Media music1 = new Media(new File(exitSound).toURI().toString());
			
			JLabel label = new JLabel(new ImageIcon(stanza1)); 
			
			exit = new JButton();
		    exit.setLocation(width*95/100,0);
		    exit.setSize(width*5/100,width*5/100);
		    exit.setOpaque(false);
		    exit.setContentAreaFilled(false);
		    exit.setFocusPainted(false);
		    exit.setBorderPainted(false);
		    exit.setIcon(new ImageIcon(exit1));
	        f.add(exit);
	        
	        exit.addMouseListener(new MouseAdapter()
	        {
	            public void mouseEntered(MouseEvent evt)
	            {

	            	MediaPlayer mediaPlayer = new MediaPlayer(hit);
					mediaPlayer.play();  
	            }
	            
	            public void mouseExited(MouseEvent evt)
	            {
	            	
	            	MediaPlayer mediaPlayer = new MediaPlayer(hit);
					mediaPlayer.play();
			        
	            }
	            
	            public void mousePressed(MouseEvent evt)
	            {
	            	MediaPlayer mediaPlayer3 = new MediaPlayer(music1);
	        		mediaPlayer3.play();
	                f.dispose();
	            }
	            
	        });
	        
	        coins = new JTextField("10");
	        coins.setLocation((int)(width*11.3/100),(int)(height*87.2/100));
	        coins.setSize(width*4/100,(int)(height*4/100));
	        coins.setOpaque(false);
	        coins.setFont(font);
	        coins.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	        f.add(coins);
	        
	        woods = new JTextField("22");
	        woods.setLocation((int)(width*27.7/100),(int)(height*87.7/100));
	        woods.setSize(width*4/100,(int)(height*4/100));
	        woods.setOpaque(false);
	        woods.setFont(font);
	        woods.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	        f.add(woods);
	        
	        stones = new JTextField("31");
	        stones.setLocation((int)(width*43.3/100),(int)(height*86.5/100));
	        stones.setSize(width*4/100,(int)(height*4/100));
	        stones.setOpaque(false);
	        stones.setFont(font);
	        stones.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	        f.add(stones);
	        
	        servants = new JTextField("44");
	        servants.setLocation((int)(width*58.4/100),(int)(height*92.3/100));
	        servants.setSize(width*4/100,(int)(height*4/100));
	        servants.setOpaque(false);
	        servants.setFont(font);
	        servants.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	        f.add(servants);
	        
	        victory = new JTextField("101");
	        victory.setLocation(width*73/100,(int)(height*85.8/100));
	        victory.setSize(width*4/100,(int)(height*4/100));
	        victory.setOpaque(false);
	        victory.setFont(font);
	        victory.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	        f.add(victory);
	        
	        military = new JTextField("31");
	        military.setLocation((int)(width*81.7/100),(int)(height*85.8/100));
	        military.setSize(width*4/100,(int)(height*4/100));
	        military.setOpaque(false);
	        military.setFont(font);
	        military.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	        f.add(military);
	        
	        faith = new JTextField("5");
	        faith.setLocation((int)(width*90.4/100),(int)(height*85.8/100));
	        faith.setSize(width*4/100,(int)(height*4/100));
	        faith.setOpaque(false);
	        faith.setFont(font);
	        faith.setBorder(javax.swing.BorderFactory.createEmptyBorder());
	        f.add(faith);
	        
	        JButton[] territories = new JButton[6];
	        JButton[] buildings = new JButton[6];
	        
	        territories = initializeButtons(territories);
	        buildings = initializeButtons(buildings);

	        territories = setLabels(territories);
	        buildings = setLabels(buildings);
	        
        	territories = locatePersonalCards(territories,false);
        	buildings = locatePersonalCards(buildings,true);
	          
	        
	        JButton[] bonusTile = new JButton[1];
	        bonusTile = initializeButtons(bonusTile);
	        bonusTile=setLabels(bonusTile);
	        bonusTile[0].setLocation((int)(width*0.07/100),(int)(height*4.38/100));
	        bonusTile[0].setSize((int)(width*5.8/100),(int)(height*72.8/100));
	        
	        
	        JLabel bonusTileLabel = new JLabel();       
	        bonusTileLabel = setImage("resources/tile/pb"+btCode+".png",5.8,73.1);
	        
	        bonusTile[0].setIcon(bonusTileLabel.getIcon());
			bonusTile[0].setDisabledIcon( bonusTile[0].getIcon() );
			
	        
			for(int j=0; j<territories.length;j++){ f.add(territories[j]); }
			for(int j=0; j<buildings.length;j++){ f.add(buildings[j]); }
			f.add(bonusTile[0]);
			
			
	        f.getContentPane().add(label);
	        f.setUndecorated(true);
	        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        
	        f.setSize(width, height);
			f.setLocation((int)((screenSize.getWidth()-f.getWidth())/2), (int)((screenSize.getHeight()-f.getHeight())/2)- (int)(screenSize.getHeight()/182.4));
	        
	        f.setResizable(false);
	        //f.setLocationRelativeTo(null);
	        f.setVisible(true);  
	        
	        
		}
		
		public void setPersonalView(){
			
			code1= 5;
			code2= 7;
			code3= 15;
			code4=19;
			btCode=1;
			
		}
		
		private JButton[] initializeButtons(JButton...btns){
			
			for (int j=0;j<btns.length;j++) {
		        btns[j]=new JButton();
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
		
		private JButton[] locatePersonalCards(JButton[] btns, boolean buildings){
			double x=6.5;
			double y=3;
			
			if(buildings)y=y+40;
			
			for(int j=0;j<btns.length;j++){
				
				btns[j].setLocation((int)(width*x/100),(int)(height*y/100));
				btns[j].setSize((int)(width*15.4/100),height*32/100);
				x=x+15.3;		
			}
			return btns;
		}
		
		public void close(){
			f.dispose();
		}
		
		private JLabel setImage(String s,double xMod, double yMod) throws IOException{
			
			BufferedImage image = ImageIO.read(new File(s)); 
			
			BufferedImage board = new BufferedImage((int)(width*xMod/100), (int)(height*yMod/100), BufferedImage.TYPE_INT_ARGB);
			
			Graphics g = board.createGraphics();
	        g.drawImage(image, 0, 0, (int)(width*xMod/100), (int)(height*yMod/100), null);
	        g.dispose();
	        
	        JLabel label = new JLabel(new ImageIcon(board)); 
	        
	        return label;
			
		}
		

		
}
