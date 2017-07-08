package it.polimi.ingsw.ps06.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;

import it.polimi.ingsw.ps06.view.BoardGUI.Direction;

public class Animations {

	private static double distance;
	private static int runTime = 1000;
	private static long startTime = -1;
	private static double smooth;
	private static int phase=1;
	private static boolean change=true;
	private static Dimension screenSize;



	/**
	 * Animazione tra due frame in base alla direzione scelta
	 * 
	 * @param f1
	 * @param f2
	 * @param direction
	 */
	public static void Animation(JInternalFrame f1, JInternalFrame f2, Direction direction){


		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		distance = 9 * ((screenSize.getHeight()/1080)*2);
		smooth = 10 * ((screenSize.getHeight()/1080));

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception ex) {
				}

				Timer timer = new Timer(40, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						if (startTime < 0) {
							startTime = System.currentTimeMillis();
						}

						long now = System.currentTimeMillis();
						long dif = now - startTime;

						if (dif > runTime && phase ==1) {
							dif=0;
							startTime=now;
							phase=2;
						}

						if (dif > runTime && phase ==2) {
							dif=runTime; 
							((Timer)e.getSource()).stop();
						}

						double progress = ((double)dif / (double)runTime)*distance;

						Point location = f1.getLocation();
						Point to = new Point(location);

						Point location2 = f2.getLocation();

						if(phase==2 && change){
							change=false;
							f2.toFront();
						}

						if(direction==Direction.TOP){
							if(phase==1){
								to.x = f1.getX();
								to.y = f1.getY() - (int)Math.round(smooth * progress);
							}
							else{
								to.x = f1.getX();
								to.y = f1.getY() + (int)Math.round(smooth * progress);
							}

							if(to.y>location2.y) f1.setLocation(f2.getLocation());
							else f1.setLocation(to);
						}

						if(direction==Direction.RIGHT){
							if(phase==1){
								to.x = f1.getX() + (int)Math.round(smooth * progress);
								to.y = f1.getY();
							}
							else{
								to.x = f1.getX() - (int)Math.round(smooth * progress);
								to.y = f1.getY();
							}
							if(to.x<location2.x) f1.setLocation(f2.getLocation());
							else f1.setLocation(to);   

						}

						if(direction==Direction.LEFT){
							if(phase==1){
								to.x = f1.getX() - (int)Math.round(smooth * progress);
								to.y = f1.getY();
							}
							else{
								to.x = f1.getX() + (int)Math.round(smooth * progress);
								to.y = f1.getY();
							}
							if(to.x>location2.x) f1.setLocation(f2.getLocation());
							else f1.setLocation(to);   
						}

						if(direction==Direction.BOTTOM){
							if(phase==1){
								to.x = f1.getX();
								to.y = f1.getY() + (int)Math.round(smooth * progress);
							}
							else{
								to.x = f1.getX();
								to.y = f1.getY() - (int)Math.round(smooth * progress);
							}

							if(to.y<location2.y) f1.setLocation(f2.getLocation());
							else f1.setLocation(to);


						}

						//f2.setLocation(to);
					}
				});
				timer.setRepeats(true);
				timer.setCoalesce(true);
				timer.start();

			}

		});


		phase=1;
		change=true;	
		distance = 9;
		runTime = 1000;
		startTime = -1;
	}

	public static void startAnimation(JInternalFrame f1, JInternalFrame f2, Direction direction, int savex, int savey){


		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception ex) {
				}

				runTime = 700;
				screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				distance = 2.2*((screenSize.getHeight()/1080));
				smooth = 1 * ((screenSize.getHeight()/1080));

				Timer timer = new Timer(40, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						if (startTime < 0) {
							startTime = System.currentTimeMillis();
						}

						long now = System.currentTimeMillis();
						long dif = now - startTime;

						Point location = f1.getLocation();
						Point to = new Point(location);

						Point location2 = f2.getLocation();
						Point to2 = new Point(location2);

						if (dif > runTime && phase ==1) {
							dif=0;
							startTime=now;
							phase=2;
						}

						if (dif > runTime && phase ==2) {
							dif=0;
							startTime=now;
							phase=3;
						}

						if (dif > runTime && phase ==3) {
							dif=0;
							startTime=now;
							phase=4;
						}

						if (dif > runTime && phase ==4){
							dif=runTime; 
							phase=5;
						}

						double progress = ((double)dif / (double)runTime)*distance;


						if(direction==Direction.RIGHT){
							if(phase==1 || phase==4){
								to.x = f1.getX() + (int)Math.round(smooth * progress);
								to2.x = f2.getX() - (int)Math.round(smooth * progress);
								f1.setLocation(to);
								f2.setLocation(to2);
							}
							if(phase==2 || phase ==3){
								to.x = f1.getX() - (int)Math.round(smooth * progress);
								to2.x = f2.getX() + (int)Math.round(smooth * progress);
								f1.setLocation(to);
								f2.setLocation(to2);
							}

						}

						if(direction==Direction.LEFT){
							if(phase==1 || phase ==4){
								to.x = f1.getX() - (int)Math.round(smooth * progress);
								to2.x = f2.getX() + (int)Math.round(smooth * progress);
								f1.setLocation(to);
								f2.setLocation(to2);
							}
							if(phase==2 || phase==3 ){
								to.x = f1.getX() + (int)Math.round(smooth * progress);
								to2.x = f2.getX() - (int)Math.round(smooth * progress);
								f1.setLocation(to);
								f2.setLocation(to2);
							}
						}

						if(phase==5){
							f1.setLocation(savex,savey);
							f2.setLocation(savex,savey);
							phase=6;
						}

						if(phase==6){
							((Timer)e.getSource()).stop();
						}
					}
				});
				timer.setRepeats(true);
				timer.setCoalesce(true);
				timer.start();

			}

		});


		phase=1;
		change=true;	
		distance = 3;
		runTime = 500;
	}


	public static void AnimationPV(JFrame f1){	

		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		distance = 17 * ((screenSize.getHeight()/1080)*2);
		smooth = 5 * ((screenSize.getHeight()/1080));
		startTime=-1;
		runTime=600;

		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception ex) {
				}

				Timer timer = new Timer(40, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						if (startTime < 0) {
							startTime = System.currentTimeMillis();
						}

						long now = System.currentTimeMillis();
						long dif = now - startTime;

						if (dif > runTime) {
							dif=runTime; 
							((Timer)e.getSource()).stop();
						}

						double progress = ((double)dif / (double)runTime)*distance;

						Point location = f1.getLocation();
						Point to = new Point(location);

						to.y = f1.getY() + (int)Math.round(smooth * progress);

						if(to.y > ( (int)( ((screenSize.getHeight()-f1.getHeight())/2) - ((int)(screenSize.getHeight()/(300))) ) )){
							((Timer)e.getSource()).stop();
							f1.setLocation((int)((screenSize.getWidth()-f1.getWidth())/2), (int)((screenSize.getHeight()-f1.getHeight())/2)- (int)(screenSize.getHeight()/300));
						}
						else f1.setLocation(to);

					}
				});
				timer.setRepeats(true);
				timer.setCoalesce(true);
				timer.start();

			}

		});	
	}

	public static void AnimationPV2(JFrame f1){	

		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		distance = 17 * ((screenSize.getHeight()/1080)*2);
		smooth = 5 * ((screenSize.getHeight()/1080));
		startTime=-1;
		runTime=600;

		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception ex) {
				}

				Timer timer = new Timer(40, new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						if (startTime < 0) {
							startTime = System.currentTimeMillis();
						}

						long now = System.currentTimeMillis();
						long dif = now - startTime;

						if (dif > runTime) {
							dif=runTime; 
							((Timer)e.getSource()).stop();
						}

						double progress = ((double)dif / (double)runTime)*distance;

						Point location = f1.getLocation();
						Point to = new Point(location);

						to.y = f1.getY() + (int)Math.round(smooth * progress);

						if(to.y > (int)(screenSize.getHeight() )){
							((Timer)e.getSource()).stop();
							f1.dispose();

						}
						else f1.setLocation(to);

					}
				});
				timer.setRepeats(true);
				timer.setCoalesce(true);
				timer.start();

			}

		});	
	}

}
