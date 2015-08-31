package com.chenqf.test;

import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JPanel;
import javax.swing.JWindow;
import com.sun.awt.AWTUtilities;

/** * @author lifetime * */
public class MerryChristmas extends JWindow implements Runnable {
	private static final long serialVersionUID = 1L;
	private static final int[] WindType = new int[] { -1, 1 };
	private Image[] xueHuaImages;
	private Dimension screenSize;
	private Vector<XueHua> list;
	private Lock lock;

	public MerryChristmas() {
		list = new Vector<XueHua>();
		lock = new ReentrantLock();
		initImages();
		setAlwaysOnTop(true);
		JPanel rootPanel = new JPanel();
		setContentPane(rootPanel);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(screenSize);
		AWTUtilities.setWindowOpaque(this, false);
		URL audioPath = getClass().getResource("music.wav");
		AudioClip audio = JApplet.newAudioClip(audioPath);
		audio.loop();
		this.setVisible(true);
		Thread t = new Thread(new Runnable() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(300);
						create();
						repaint();
					} catch (InterruptedException e) {
					}
				}
			}
		});
		t.start();
	}

	public void paint(Graphics g) {
		super.paint(g);
		if (lock.tryLock()) {
			try {
				for (Iterator<XueHua> iterator = list.iterator(); iterator
						.hasNext();) {
					XueHua xh = iterator.next();
					g.drawImage(xh.img, xh.x, xh.y, null);
				}
			} finally {
				lock.unlock();
			}
		}
	}

	void initImages() {
		xueHuaImages = new Image[4];
		try {
			for (int i = 1; i <= 4; i++) {
				xueHuaImages[i - 1] = ImageIO.read(getClass()
						.getResourceAsStream(i + ".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void create() {
		XueHua e = randomNext(0);
		if (lock.tryLock()) {
			try {
				list.add(e);
			} finally {
				lock.unlock();
			}
		}
	}

	XueHua randomNext(int y) {
		Random random = new Random();
		int x = random.nextInt(screenSize.width);
		XueHua hua = new XueHua();
		hua.x = x;
		hua.y = y;
		hua.wind = WindType[random.nextInt(2)];
		hua.windSpeed = hua.wind * 3;
		hua.img = xueHuaImages[random.nextInt(xueHuaImages.length)];
		hua.x += hua.img.getWidth(null);
		if (hua.x >= screenSize.width) {
			hua.x = screenSize.width - hua.img.getWidth(null);
		}
		return hua;
	}

	public void run() {
		while (true) {
			try {
				Thread.sleep(50);
				down();
				repaint();
			} catch (InterruptedException e) {
			}
		}
	}

	protected void down() {
		if (lock.tryLock()) {
			try {
				for (Iterator<XueHua> it = list.iterator(); it.hasNext();) {
					XueHua xh = it.next();
					if (xh.y > screenSize.height) {
						it.remove();
						continue;
					}
					if (xh.x < 0
							|| xh.x > (screenSize.width - xh.img.getWidth(null))) {
						xh.wind *= -1;
						xh.windSpeed *= -1;
					}
					xh.x += xh.windSpeed;
					xh.y += 3;
				}
			} finally {
				lock.unlock();
			}
		}
	}

	class XueHua {
		public int x;
		public int y;
		public int wind;
		public int windSpeed;
		public Image img;
	}

	public static void main(String[] args) {
		Thread start = new Thread(new MerryChristmas());
		start.start();
	}
}