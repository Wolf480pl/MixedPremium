package com.github.wolf480pl.mixedpremium;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.craftbukkit.CraftServer;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
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
		if(authed.get(socket)) return false;
		if(premium.get(socket))
			return getConfig().getBoolean("premium-login");
		else
			return getConfig().getBoolean("others-login");
	}
	public void setPremium(Socket socket, boolean premium){
		this.premium.put(socket, premium);
	}
	public void setPremium(Player player, boolean premium){
		InetSocketAddress addr = player.getAddress();
		
		
	}


}
