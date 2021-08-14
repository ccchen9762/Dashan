package FJ104502033;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState{
	
	private SpriteSheet pingSheet;
	private Animation pingAnimation;
	private Rectangle ping = null;
	private Rectangle flags = null;
	private Rectangle bricks[];
	private Rectangle wall1[];
	private Rectangle wall2[];
	private Rectangle wall3[];
	private Rectangle wall4[];
	private Rectangle wall5[];
	private Rectangle block1[];
	private Rectangle block2[];
	private Rectangle monsters[];
	private boolean Monster[];
	private ArrayList <Rectangle> tools;
	private Music music;
	private int timepass;
	private boolean jumping = false;
	private boolean falling = false;
	private boolean fail = false;
	int number=0;
	
	public Play(int state){
	}
	
	@Override
	public void init(GameContainer gc, StateBasedGame state) throws SlickException {
		pingSheet = new SpriteSheet("Pic/walk.png", 53, 76);
		pingAnimation = new Animation(pingSheet, 200);
		ping = new Rectangle(350, 424, 54, 76);
		flags = new Rectangle(10000, 0, 100, 600);
		bricks = new Rectangle[450];
		wall1 = new Rectangle[10];
		wall2 = new Rectangle[10];
		wall3 = new Rectangle[14];
		wall4 = new Rectangle[10];
		wall5 = new Rectangle[44];
		block1 = new Rectangle[22];
		block2 = new Rectangle[21];
		monsters = new Rectangle[12];
		Monster = new boolean[12];
		tools = new ArrayList <Rectangle>();
		Music music = new Music("Sound/080.wav");
		
		for(int i=0 ; i < 450 ; i++){//磚塊
			if(i==138||i==139||i==140||i==141||i==172||i==173||i==174||i==175||i==176||i==177||i==308||i==309||i==310||i==311)
				bricks[i] = new Rectangle(0, 550, 50, 50);
			else{
				if(i%2 == 1)
					bricks[i] = new Rectangle( ((i/2)*50), 500, 50, 50);	
				else
					bricks[i] = new Rectangle( (((i+1)/2)*50), 550, 50, 50);			
			}
		}
		
		for(int mon=0 ; mon<12 ; mon++){//怪物位置
			if(mon<8)
				monsters[mon] = new Rectangle((600*(mon+3)), 450, 50, 50);
			if(mon==8)
				monsters[mon] = new Rectangle((79*50), 250, 50, 50);
			if(mon==9)
				monsters[mon] = new Rectangle((108*50), 250, 50, 50);
			if(mon==10)
				monsters[mon] = new Rectangle((172*50), 250, 50, 50);
			if(mon==11)
				monsters[mon] = new Rectangle((87*50), 250, 50, 50);
		}
		for(int i=0 ; i<12 ; i++){//怪初始沒有死
			Monster[i] = false;
		}
		
		for(int i=0 ; i<22 ; i++){//浮空磚1位置
			if(i==0)
				block1[i] = new Rectangle(16*50, 300, 50, 50);
			if(i<=5 && i>=1)
				block1[i] = new Rectangle((20+(i-1))*50, 300, 50, 50);
			if(i==6)
				block1[i] = new Rectangle(64*50, 250, 50, 50);
			if(i>=7 && i<=9)
				block1[i] = new Rectangle((77+(i-7))*50, 300, 50, 50);
			if(i==10)
				block1[i] = new Rectangle(94*50, 300, 50, 50);
			if(i>=11 && i<=12)
				block1[i] = new Rectangle((100+(i-11))*50, 300, 50, 50);
			if(i>=13 && i<=15)
				block1[i] = new Rectangle((106+(i-13)*3)*50, 300, 50, 50);
			if(i==16)
				block1[i] = new Rectangle(118*50, 300, 50, 50);
			if(i>=17 && i<=18)
				block1[i] = new Rectangle((129+(i-17))*50, 300, 50, 50);
			if(i>=19 && i<=22)
				block1[i] = new Rectangle((169+(i-19))*50, 300, 50, 50);
		}
		for(int i=0 ; i<21 ; i++){//浮空磚2位置
			if(i==0)
				block2[i] = new Rectangle(22*50, 100, 50, 50);
			if(i<=8 && i>=1)
				block2[i] = new Rectangle((80+(i-1))*50, 100, 50, 50);
			if(i>=9 && i<=12)
				block2[i] = new Rectangle((91+(i-9))*50, 100, 50, 50);
			if(i==13)
				block2[i] = new Rectangle(109*50, 100, 50, 50);
			if(i>=14 && i<=16)
				block2[i] = new Rectangle((121+(i-14))*50, 100, 50, 50);
			if(i>=17 && i<=20)
				block2[i] = new Rectangle((128+(i-17))*50, 100, 50, 50);
		}
		
		for(int j=0 ; j<44 ; j++){//牆壁位置
			if(j == 0){
				wall1[j] = new Rectangle(134*50, 450, 50, 50);
				wall2[j] = new Rectangle(144*50, 450, 50, 50);
				wall3[j] = new Rectangle(149*50, 450, 50, 50);
				wall4[j] = new Rectangle(159*50, 450, 50, 50);
				wall5[j] = new Rectangle(182*50, 450, 50, 50);
			}
			if(j>=1 && j<=2){
				wall1[j] = new Rectangle(135*50, 450-50*(j-1), 50, 50);
				wall2[j] = new Rectangle(143*50, 450-50*(j-1), 50, 50);
				wall3[j] = new Rectangle(150*50, 450-50*(j-1), 50, 50);
				wall4[j] = new Rectangle(158*50, 450-50*(j-1), 50, 50);
				wall5[j] = new Rectangle(183*50, 450-50*(j-1), 50, 50);
			}
			if(j>=3 && j<=5){
				wall1[j] = new Rectangle(136*50, 450-50*(j-3), 50, 50);
				wall2[j] = new Rectangle(142*50, 450-50*(j-3), 50, 50);
				wall3[j] = new Rectangle(151*50, 450-50*(j-3), 50, 50);
				wall4[j] = new Rectangle(157*50, 450-50*(j-3), 50, 50);
				wall5[j] = new Rectangle(184*50, 450-50*(j-3), 50, 50);
			}
			if(j>=6 && j<=9){
				wall1[j] = new Rectangle(137*50, 450-50*(j-6), 50, 50);
				wall2[j] = new Rectangle(141*50, 450-50*(j-6), 50, 50);
				wall3[j] = new Rectangle(152*50, 450-50*(j-6), 50, 50);
				wall4[j] = new Rectangle(156*50, 450-50*(j-6), 50, 50);
				wall5[j] = new Rectangle(185*50, 450-50*(j-6), 50, 50);
			}
			if(j>=10 && j<=14){
				if(j<=13)
					wall3[j] = new Rectangle(153*50, 450-50*(j-10), 50, 50);
				wall5[j] = new Rectangle(186*50, 450-50*(j-10), 50, 50);
			}
			if(j>=15 && j<=20)
				wall5[j] = new Rectangle(187*50, 450-50*(j-15), 50, 50);
			if(j>=21 && j<=27)
				wall5[j] = new Rectangle(188*50, 450-50*(j-21), 50, 50);
			if(j>=28 && j<=35)
				wall5[j] = new Rectangle(189*50, 450-50*(j-28), 50, 50);
			if(j>=36 && j<=43)
				wall5[j] = new Rectangle(190*50, 450-50*(j-36), 50, 50);
		}
	}

	@Override
	public void render(GameContainer gc, StateBasedGame state, Graphics g) throws SlickException {
		
		Input input = gc.getInput();
		Image gbg = new Image("Pic/gbg.png");
		Image stand = new Image("Pic/stand.png");
		Image tool = new Image("Pic/tool.png");
		Image monster = new Image("Pic/monster.png");
		Image brick = new Image("Pic/brick.png");
		Image wall = new Image("Pic/wall.png");
		Image flag = new Image("Pic/flag.png");
		Image blocks = new Image("Pic/block.png");

		g.drawImage(gbg, 0, 0);
		g.texture(flags, flag, 1, 1, true);
		
		for(int i=0 ; i<450 ; i++){//磚塊
			g.texture(bricks[i] ,brick ,1 ,1 ,true);
		}
		
		for(int j=0 ; j<44 ; j++){
			if(j<10){
				g.texture(wall1[j] ,wall, 1, 1, true);
				g.texture(wall2[j] ,wall, 1, 1, true);
				g.texture(wall4[j] ,wall, 1, 1, true);
			}
			if(j<14)
				g.texture(wall3[j] ,wall, 1, 1, true);
			g.texture(wall5[j] ,wall, 1, 1, true);
		}
		
		if(input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_LEFT)) //人物
			pingAnimation.draw(ping.getX(), ping.getY());
		else
			g.texture(ping, stand ,1 ,1 ,true);	
		
		for(number=0 ; number<tools.size() ; number++){//畫板手
			g.texture(tools.get(number), tool, 1, 1, true);
		}
		for(int mon=0 ; mon<12 ; mon++){//畫怪物
			if(!Monster[mon])
				g.texture(monsters[mon], monster, 1, 1, true);
		}
		for(int p=0 ; p<22 ; p++){//畫浮空磚1
			g.texture(block1[p], blocks, 1, 1, true);
		}
		for(int q=0 ; q<21 ; q++){//畫浮空磚2
			g.texture(block2[q], blocks, 1, 1, true);
		}
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame state, int k) throws SlickException {		
		
		pingAnimation.update(k);
		Input input = gc.getInput();
		Music music = new Music("Sound/080.wav");
		
		for(int i=0 ; i<450 ; i++){//判斷有無踩在方塊上
			if( i==449 ){
				if(!falling)
					timepass=0;
				falling = true;
			}
			if((ping.intersects(bricks[i]) && ping.getX()>=bricks[i].getX()-45 && (ping.getX()+53)<=bricks[i].getX()+50+45 && ping.getY()+76 <= bricks[i].getY()+5)|| jumping == true){
				falling = false;
				break;
			}
			if(i<10){
				if(ping.intersects(wall1[i]) && ping.getX()>=wall1[i].getX()-45 && (ping.getX()+53)<=wall1[i].getX()+50+45 && ping.getY()+76 <= wall1[i].getY()+5){
					falling = false;
					break;
				}
				if(ping.intersects(wall2[i]) && ping.getX()>=wall2[i].getX()-45 && (ping.getX()+53)<=wall2[i].getX()+50+45 && ping.getY()+76 <= wall2[i].getY()+5){
					falling = false;
					break;
				}
				if(ping.intersects(wall4[i]) && ping.getX()>=wall4[i].getX()-45 && (ping.getX()+53)<=wall4[i].getX()+50+45 && ping.getY()+76 <= wall4[i].getY()+5){
					falling = false;
					break;
				}
			}
			if(i<14){
				if(ping.intersects(wall3[i]) && ping.getX()>=wall3[i].getX()-45 && (ping.getX()+53)<=wall3[i].getX()+50+45 && ping.getY()+76 <= wall3[i].getY()+5){
					falling = false;
					break;
				}
			}
			if(i<44){
				if(ping.intersects(wall5[i]) && ping.getX()>=wall5[i].getX()-45 && (ping.getX()+53)<=wall5[i].getX()+50+45 && ping.getY()+76 <= wall5[i].getY()+5){
					falling = false;
					break;
				}
			}
			if(i<22){
				if(ping.intersects(block1[i]) && ping.getX()>=block1[i].getX()-45 && (ping.getX()+53)<=block1[i].getX()+50+45 && ping.getY()+76 <= block1[i].getY()+5){
					falling = false;
					break;
				}
			}
			if(i<21){
				if(ping.intersects(block2[i]) && ping.getX()>=block2[i].getX()-45 && (ping.getX()+53)<=block2[i].getX()+50+45 && ping.getY()+76 <= block2[i].getY()+5){
					falling = false;
					break;
				}
			}
		}
		
		if(input.isKeyDown(Input.KEY_RIGHT)){//右右
			if(bricks[440].getX()<=850){//到右底
				ping.setCenterX(350);
			}
			for(int a=0 ; a<450 ; a++){//向右碰撞
				if(ping.intersects(bricks[a]) && ping.getY()>=bricks[a].getY()-75 && (ping.getY()+76)<=bricks[a].getY()+50+75 && ping.getX()+54<=bricks[a].getX()+5){
						ping.setX(bricks[a].getX()-70);//碰到地板
						break;
				}
				if(a<10){//碰到牆
					if(ping.intersects(wall1[a]) && ping.getY()>=wall1[a].getY()-75 && (ping.getY()+76)<=wall1[a].getY()+50+75 && ping.getX()+54<=wall1[a].getX()+5){
						ping.setX(wall1[a].getX()-70);
						break;
					}
					if(ping.intersects(wall2[a]) && ping.getY()>=wall2[a].getY()-75 && (ping.getY()+76)<=wall2[a].getY()+50+75 && ping.getX()+54<=wall2[a].getX()+5){
						ping.setX(wall2[a].getX()-70);
						break;
					}
					if(ping.intersects(wall4[a]) && ping.getY()>=wall4[a].getY()-75 && (ping.getY()+76)<=wall4[a].getY()+50+75 && ping.getX()+54<=wall4[a].getX()+5){
						ping.setX(wall4[a].getX()-70);
						break;
					}
				}
				if(a<14){
					if(ping.intersects(wall3[a]) && ping.getY()>=wall3[a].getY()-75 && (ping.getY()+76)<=wall3[a].getY()+50+75 && ping.getX()+54<=wall3[a].getX()+5){
						ping.setX(wall3[a].getX()-70);
						break;
					}
				}
				if(a<44){
					if(ping.intersects(wall5[a]) && ping.getY()>=wall5[a].getY()-75 && (ping.getY()+76)<=wall5[a].getY()+50+75 && ping.getX()+54<=wall5[a].getX()+5){
						ping.setX(wall5[a].getX()-70);
						break;
					}
				}
				if(a<22){//碰到浮空磚
					if(ping.intersects(block1[a]) && ping.getY()>=block1[a].getY()-75 && (ping.getY()+76)<=block1[a].getY()+50+75 && ping.getX()+54<=block1[a].getX()+5){
						ping.setX(block1[a].getX()-70);
						break;
					}
				}
				if(a<21){
					if(ping.intersects(block2[a]) && ping.getY()>=block2[a].getY()-75 && (ping.getY()+76)<=block2[a].getY()+50+75 && ping.getX()+54<=block2[a].getX()+5){
						ping.setX(block2[a].getX()-70);
						break;
					}
				}
			}
			if(ping.getX() <= 350){//人移動
				ping.setCenterX((float)(ping.getCenterX()+8.0));
			}
			else{//畫面移動
				flags.setCenterX((float)(flags.getCenterX()-8.0));
				for(int i=0 ; i<450 ; i++)
					bricks[i].setCenterX((float)(bricks[i].getCenterX()-8.0));
				for(int j=0 ; j<44 ; j++){
					if(j<10){
						wall1[j].setCenterX((float)(wall1[j].getCenterX()-8.0));
						wall2[j].setCenterX((float)(wall2[j].getCenterX()-8.0));
						wall4[j].setCenterX((float)(wall4[j].getCenterX()-8.0));
					}
					if(j<14)
						wall3[j].setCenterX((float)(wall3[j].getCenterX()-8.0));		
					wall5[j].setCenterX((float)(wall5[j].getCenterX()-8.0));	
				}
				for(int mon=0 ; mon<12 ; mon++){
					monsters[mon].setCenterX((float)(monsters[mon].getCenterX()-8.0));
				}
				for(int i=0 ; i<22 ; i++){
					block1[i].setCenterX((float)(block1[i].getCenterX()-8.0));
				}
				for(int i=0 ; i<21 ; i++){
					block2[i].setCenterX((float)(block2[i].getCenterX()-8.0));
				}
			}
		}
		
		if(input.isKeyDown(Input.KEY_LEFT)){//左左
			if(ping.getX() <= 0)//到左底
				ping.setX(0);
			for(int b=0 ; b<450 ; b++){//向左碰撞
				if(ping.intersects(bricks[b]) && ping.getY()>=bricks[b].getY()-75 && (ping.getY()+76)<=bricks[b].getY()+50+75 && ping.getX()>=bricks[b].getX()+45){
					ping.setX(bricks[b].getX()+70);
					break;
				}
				if(b<10){//碰到牆
					if(ping.intersects(wall1[b]) && ping.getY()>=wall1[b].getY()-75 && (ping.getY()+76)<=wall1[b].getY()+50+75 && ping.getX()>=wall1[b].getX()+45){
						ping.setX(wall1[b].getX()+70);
						break;
					}
					if(ping.intersects(wall2[b]) && ping.getY()>=wall2[b].getY()-75 && (ping.getY()+76)<=wall2[b].getY()+50+75 && ping.getX()>=wall2[b].getX()+45){
						ping.setX(wall2[b].getX()+70);
						break;
					}
					if(ping.intersects(wall4[b]) && ping.getY()>=wall4[b].getY()-75 && (ping.getY()+76)<=wall4[b].getY()+50+75 && ping.getX()>=wall4[b].getX()+45){
						ping.setX(wall4[b].getX()+70);
						break;
					}
				}
				if(b<14){
					if(ping.intersects(wall3[b]) && ping.getY()>=wall3[b].getY()-75 && (ping.getY()+76)<=wall3[b].getY()+50+75 && ping.getX()>=wall3[b].getX()+45){
						ping.setX(wall3[b].getX()+70);
						break;
					}
				}
				if(b<44){
					if(ping.intersects(wall5[b]) && ping.getY()>=wall5[b].getY()-75 && (ping.getY()+76)<=wall5[b].getY()+50+75 && ping.getX()>=wall5[b].getX()+45){
						ping.setX(wall5[b].getX()+70);
						break;
					}
				}
				if(b<22){//碰到浮空磚
					if(ping.intersects(block1[b]) && ping.getY()>=block1[b].getY()-75 && (ping.getY()+76)<=block1[b].getY()+50+75 && ping.getX()>=block1[b].getX()+45){
						ping.setX(block1[b].getX()+70);
						break;
					}
				}
				if(b<21){
					if(ping.intersects(block2[b]) && ping.getY()>=block2[b].getY()-75 && (ping.getY()+76)<=block2[b].getY()+50+75 && ping.getX()>=block2[b].getX()+45){
						ping.setX(block2[b].getX()+70);
						break;
					}
				}
			}
			if(bricks[0].getX() == 0 || ping.getX()>=360){//人移動
				ping.setCenterX((float)(ping.getCenterX()-8.0));
			}
			else{//畫面移動
				flags.setCenterX((float)(flags.getCenterX()+8.0));
				for(int i=0 ; i<450 ; i++)//磚塊
					bricks[i].setCenterX((float)(bricks[i].getCenterX()+8.0));
				for(int j=0 ; j<44 ; j++){//牆
					if(j<10){ 
						wall1[j].setCenterX((float)(wall1[j].getCenterX()+8.0));
						wall2[j].setCenterX((float)(wall2[j].getCenterX()+8.0));
						wall4[j].setCenterX((float)(wall4[j].getCenterX()+8.0));
					}
					if(j<14)
						wall3[j].setCenterX((float)(wall3[j].getCenterX()+8.0));		
					wall5[j].setCenterX((float)(wall5[j].getCenterX()+8.0));	
				}
				for(int mon=0 ; mon<12 ; mon++){
					monsters[mon].setCenterX((float)(monsters[mon].getCenterX()+8.0));
				}
				for(int i=0 ; i<22 ; i++){
					block1[i].setCenterX((float)(block1[i].getCenterX()+8.0));
				}
				for(int i=0 ; i<21 ; i++){
					block2[i].setCenterX((float)(block2[i].getCenterX()+8.0));
				}
			}
		}
		
		if(falling == false && input.isKeyPressed(Input.KEY_UP)){//跳跳
			if(!jumping)//歸零時間
				timepass=0;
			jumping=true;
		}
		if(jumping)//執行跳躍函式
			jump(ping);
	
		if(falling == true){//掉落處理
			ping.setCenterY((float)(ping.getCenterY()+(300*timepass)/2500));
		}	
		if(falling == true || jumping == true){//碰到地板
			for(int i=0 ; i<450 ; i++){
				if(ping.intersects(bricks[i]) && ping.getX()>=bricks[i].getX()-45 && (ping.getX()+53)<=bricks[i].getX()+50+45 && ping.getY()<=bricks[i].getY()){
					jumping = false;
					falling = false;
					ping.setY(bricks[i].getY()-76);
					break;
				}
				if(i<10){
					if(ping.intersects(wall1[i]) && ping.getX()>=wall1[i].getX()-45 && (ping.getX()+53)<=wall1[i].getX()+50+45 && ping.getY()<=wall1[i].getY()){
						jumping = false;
						falling = false;
						ping.setY(wall1[i].getY()-76);
						break;
					}
					if(ping.intersects(wall2[i]) && ping.getX()>=wall2[i].getX()-45 && (ping.getX()+53)<=wall2[i].getX()+50+45 && ping.getY()<=wall2[i].getY()){
						jumping = false;
						falling = false;
						ping.setY(wall2[i].getY()-76);
						break;
					}
					if(ping.intersects(wall4[i]) && ping.getX()>=wall4[i].getX()-45 && (ping.getX()+53)<=wall4[i].getX()+50+45 && ping.getY()<=wall4[i].getY()){
						jumping = false;
						falling = false;
						ping.setY(wall4[i].getY()-76);
						break;
					}
				}
				if(i<14){
					if(ping.intersects(wall3[i]) && ping.getX()>=wall3[i].getX()-45 && (ping.getX()+53)<=wall3[i].getX()+50+45 && ping.getY()<=wall3[i].getY()){
						jumping = false;
						falling = false;
						ping.setY(wall3[i].getY()-76);
						break;
					}
				}
				if(i<44){
					if(ping.intersects(wall5[i]) && ping.getX()>=wall5[i].getX()-45 && (ping.getX()+53)<=wall5[i].getX()+50+45 && ping.getY()<=wall5[i].getY()){
						jumping = false;
						falling = false;
						ping.setY(wall5[i].getY()-76);
						break;
					}
				}
				if(i<22){
					if(ping.intersects(block1[i]) && ping.getX()>=block1[i].getX()-45 && (ping.getX()+53)<=block1[i].getX()+50+45 && ping.getY()<=block1[i].getY()){
						jumping = false;
						falling = false;
						ping.setY(block1[i].getY()-76);
						break;
					}
				}
				if(i<21){
					if(ping.intersects(block2[i]) && ping.getX()>=block2[i].getX()-45 && (ping.getX()+53)<=block2[i].getX()+50+45 && ping.getY()<=block2[i].getY()){
						jumping = false;
						falling = false;
						ping.setY(block2[i].getY()-76);
						break;
					}
				}
			}
		}
		if(jumping == true){//頂到處理
			for(int i=0 ; i<22 ; i++){
				if(i<22){
					if(ping.intersects(block1[i]) && ping.getX()>=block1[i].getX()-45 && (ping.getX()+53)<=block1[i].getX()+50+45 && ping.getY()>=block1[i].getY()+20){
						ping.setY(block1[i].getY()+50);
						timepass=250;
						break;
					}
				}
				if(i<21){
					if(ping.intersects(block2[i]) && ping.getX()>=block2[i].getX()-45 && (ping.getX()+53)<=block2[i].getX()+50+45 && ping.getY()>=block2[i].getY()+20){
						ping.setY(block2[i].getY()+50);
						timepass=250;
						break;
					}
				}
			}
		}
		
		if(input.isKeyPressed(Input.KEY_SPACE)){//射射
			tools.add(new Rectangle(ping.getCenterX()+41, ping.getCenterY(), 50, 11));
			music.play();
			music.setVolume(10);
		}
		fly();//板手出現即開始飛行
		
		move();//怪物動作方式
		
		timepass = timepass + k;//時間函數增加
		
		for(int mon=0 ; mon<12 ; mon++){//碰到怪物就死
			if(!Monster[mon])
				if(ping.intersects(monsters[mon]))
					fail = true;
		}
		if(ping.getY()>=610 || fail){//失敗
			JOptionPane.showMessageDialog(null, "練練再來", "Failed", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
		if(ping.getCenterX() >= flags.getCenterX()){//贏了
			JOptionPane.showMessageDialog(null, "嗆聲成功", "Win!!", JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
	}

	@Override
	public int getID() {
		return 2;
	}
	
	private void move() {//怪物動
		for(int mon=0 ; mon<12 ; mon++){
			if((monsters[mon].getX()-ping.getX())<700){
				monsters[mon].setCenterX((float)(monsters[mon].getCenterX()-3.0));
			}
			else
				Monster[mon]=false;
		}
	}
	
	public void jump(Rectangle rect1){//跳躍 V&A
		if(timepass <= 250){
				rect1.setCenterY((float)(rect1.getCenterY()-(30-(300*timepass)/2500)));
		}
		else{
			rect1.setCenterY((float)(rect1.getCenterY()+((300*(timepass-250))/2500)));
		}
	}
	
	private void fly() {//板手飛行
		for(number=0 ; number<tools.size() ; number++){
			tools.get(number).setCenterX((float)(tools.get(number).getCenterX()+10.0));
			for(int mon=0 ; mon<12; mon++){
				if(!Monster[mon]){
					if(monsters[mon].intersects(tools.get(number))){
						tools.remove(number);
						Monster[mon]=true;
						break;
					}
				}
			}
		}
	}
}