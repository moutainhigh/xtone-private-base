/**

*Copyright 2003 Xiamen Xiangtone Co. Ltd.

*All right reserved.

*/



public class xiangtone_game

{

	public static void main(String args[])

	{

		 try

		 {

		  	System.out.print("����������Ϸ�߳̿�ʼ....");

		  	VCPServer server = new VCPServer(7200);

          	server.start();

          

          	//Thread.currentThread().sleep(2000);

          

          	System.out.println("��Ϸ��ʱ���������߳̿�ʼ...."); 

         	 //GameServer game = new GameServer();

          	//new Thread(game).start();

        }

        catch(Exception e)

        {

        	System.out.println("ϵͳ����...");

        	e.printStackTrace();

        	System.exit(0);

        }

	}

}
