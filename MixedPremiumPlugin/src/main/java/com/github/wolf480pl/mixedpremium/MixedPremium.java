package com.github.wolf480pl.mixedpremium;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Map;
import java.util.logging.Logger;

import net.minecraft.server.NetServerHandler;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class MixedPremium extends JavaPlugin implements Listener {
	private Logger log;
	private Map<Socket,Boolean> premium;
	private Map<Socket,Boolean> authed;
	public void onEnable(){
		log = getLogger();
		getServer().getPluginManager().registerEvents(this, this);
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	public boolean mustLogin(Socket socket){
		if(authed.containsKey(socket) && authed.get(socket)) return false;
		if(premium.containsKey(socket) && premium.get(socket))
			return getConfig().getBoolean("premium-login");
		else
			return getConfig().getBoolean("others-login");
	}
	public boolean mustLogin(Player player){
		NetServerHandler serverhandler = ((CraftPlayer)player).getHandle().netServerHandler; 
		if(serverhandler != null) return mustLogin(serverhandler.networkManager.getSocket());
		return true;
	}
	public void setPremium(Socket socket, boolean premium){
		this.premium.put(socket, premium);
	}
/*	public void setPremium(Player player, boolean premium){
		setPremium(((CraftPlayer)player).getHandle().netServerHandler.networkManager.getSocket(),premium);
	}*/ // Just for me to know how to get player's socket
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event){
		if(mustLogin(event.getPlayer())) event.setJoinMessage(null);
	}


}
