package org.x;
import com.xiangtone.util.ConfigManager;

/**
 * 
 * Copyright 2003 Xiamen Xiangtone Co. Ltd.
 * 
 * All right reserved.
 * 
 */

public class xiangtone_game

{

	public static void main(String args[])

	{

		try

		{
			int port = Integer.parseInt((String) ConfigManager.getConfigData("listen_port"));

			System.out.print("����������Ϸ�߳̿�ʼ....listen:" + port);

			VCPServer server = new VCPServer(port);

			server.start();

			// Thread.currentThread().sleep(2000);

			System.out.println("��Ϸ��ʱ���������߳̿�ʼ....");

			// GameServer game = new GameServer();

			// new Thread(game).start();

		}

		catch (Exception e)

		{

			System.out.println("ϵͳ����...");

			e.printStackTrace();

			System.exit(0);

		}

	}

}
