package mincra.magicrod.rod;

import mincra.magicrod.main.Magic;
import mincra.magicrod.version.Version;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class AiceRod_4 extends BukkitRunnable{
	Player player;
	Plugin plugin;
	public AiceRod_4(Player _player,Plugin _plugin) {
		player=_player;
		plugin=_plugin;
	}
	@Override
	public void run() {
		for(Entity entity:player.getNearbyEntities(15, 3, 15)){
			if(entity instanceof LivingEntity){
			final LivingEntity le=(LivingEntity) entity;
				if(le instanceof Monster||le instanceof WaterMob){
					Version.playSound(player.getLocation(),Sound.BLOCK_GLASS_BREAK, 0.5F, 1F);
					Version.playSound(le.getLocation(), Sound.BLOCK_PORTAL_TRAVEL, 0.1F, 10F);
					Vector v=new Vector(0,0,0);
					le.setVelocity(v);
					Location loc2=le.getLocation().add(-2,0,-2);
					Version.playeffect2(le.getLocation(),"FIREWORKS_SPARK");
					for(Location l:Magic.ice){
						loc2.add(l.getX(),l.getY(),l.getZ());
						final Block blo=loc2.getBlock();
						if(blo.getType().equals(Material.AIR)||blo.getType().equals(Material.LONG_GRASS)||blo.getType().equals(Material.RED_ROSE)||blo.getType().equals(Material.SNOW)){
							blo.setType(Material.ICE);
					        new BukkitRunnable() {
					        	Block clearblo=blo;
					            public void run() {
					            	clearblo.setType(Material.AIR);
					            }
					        }.runTaskLater(plugin, 400);
						}
						loc2.add(-1*l.getX(),-1*l.getY(),-1*l.getZ());
					}
					for(Location l:Magic.ice_2){
						loc2.add(l.getX(),l.getY(),l.getZ());
						final Block blo=loc2.getBlock();
						if(blo.getType().equals(Material.AIR)||blo.getType().equals(Material.LONG_GRASS)||blo.getType().equals(Material.RED_ROSE)||blo.getType().equals(Material.SNOW)){
							blo.setType(Material.ICE);
					        new BukkitRunnable() {
					        	Block clearblo=blo;
					            public void run() {
					            	clearblo.setType(Material.AIR);
					            }
					        }.runTaskLater(plugin, 400);
						}
						loc2.add(-1*l.getX(),-1*l.getY(),-1*l.getZ());
					}
			        new BukkitRunnable() {
			        	LivingEntity le2=le;
			        	int a=0;
			            public void run() {
			            	if(!le.isDead()&&le2.getLocation().add(0, 1, 0).getBlock().getType().equals(Material.ICE)){
			            		le2.damage(2D, player);
			            	}else {
			            		this.cancel();
			            	}
			            	if(a>39){
			            		this.cancel();
			            	}
			            	a++;
			            }
			        }.runTaskTimer(plugin,10,10);
				}
			}
		}
	}
}
