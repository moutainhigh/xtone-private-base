package org.x.netty.support;

import org.apache.log4j.Logger;
import org.x.NettyClient;
import org.x.SMSIsmgInfo;

import comsd.commerceware.cmpp.CMPP;
import comsd.commerceware.cmpp.CmppLogin;
import comsd.commerceware.cmpp.OutOfBoundsException;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class CmppClientHandler extends ChannelInboundHandlerAdapter {

	private final static Logger LOG = Logger.getLogger(ChannelInboundHandlerAdapter.class);

	private final NettyClient client;

	public CmppClientHandler(NettyClient nettyClient) {
		this.client = nettyClient;
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext arg0, Throwable arg1) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void handlerAdded(ChannelHandlerContext arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void handlerRemoved(ChannelHandlerContext arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		LOG.debug("channelRead");
		CmppMessage cmppMessage = (CmppMessage) msg;
		LOG.debug(cmppMessage);
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent e = (IdleStateEvent) evt;
			if (e.state() == IdleState.READER_IDLE) {
				ctx.close();
			} else if (e.state() == IdleState.WRITER_IDLE) {
				CmppMessage cmppMessage = new CmppMessage();
				cmppMessage.setCmd(8);
				cmppMessage.setSeq(NettyClient.SEQ++);
				byte[] body = new byte[0];
				cmppMessage.setBody(body);
				ctx.writeAndFlush(cmppMessage);
			}
		}
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		LOG.debug("active");
		try {
			SMSIsmgInfo smsIsmgInfo = new SMSIsmgInfo();
			CmppLogin cmppLogin = new CmppLogin();
			cmppLogin.set_icpid(SMSIsmgInfo.gd_icpID);
			cmppLogin.set_auth(SMSIsmgInfo.gd_icpShareKey);
			cmppLogin.set_version((byte) 0x30);
			cmppLogin.set_timestamp(1111101020);
			CMPP cmpp = new CMPP();
			cmpp.cmppLoginBytes(cmppLogin);
			ByteBuf firstMessage = Unpooled.copiedBuffer(cmpp.cmppLoginBytes(cmppLogin));
			LOG.debug(cmpp.cmppLoginBytes(cmppLogin));
			// ctx.writeAndFlush(firstMessage);
			CmppMessage loginCmppMessage = new CmppMessage();
			loginCmppMessage.setCmd(0x00000001);
			loginCmppMessage.setSeq(NettyClient.SEQ++);
			loginCmppMessage.setBody(cmpp.cmppLoginBodyBytes(cmppLogin));
			ctx.writeAndFlush(loginCmppMessage);
		} catch (OutOfBoundsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
