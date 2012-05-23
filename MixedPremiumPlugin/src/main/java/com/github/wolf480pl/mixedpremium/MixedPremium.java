package com.github.wolf480pl.mixedpremium;

import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class MixedPremium extends JavaPlugin implements Listener {
	private Logger log;
	private Map<String,Boolean> premiums;
	public void onEnable(){
		log = getLogger();
		getServer().getPluginManager().registerEvents(this, this);
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	public boolean mustLogin(boolean premium){
		if(premium)
			return getConfig().getBoolean("premium-login");
		else
			return getConfig().getBoolean("others-login");
	}
	public void setPremium(String name, boolean premium){
		premiums.put(name, premium);
	}

}
