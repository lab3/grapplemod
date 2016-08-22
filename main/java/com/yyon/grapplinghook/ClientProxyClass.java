package com.yyon.grapplinghook;

import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;

import com.yyon.grapplinghook.controllers.grappleController;
import com.yyon.grapplinghook.entities.RenderGrappleArrow;
import com.yyon.grapplinghook.entities.grappleArrow;
import com.yyon.grapplinghook.items.clickitem;
import com.yyon.grapplinghook.items.enderBow;
import com.yyon.grapplinghook.items.grappleBow;
import com.yyon.grapplinghook.items.hookBow;
import com.yyon.grapplinghook.items.launcherItem;
import com.yyon.grapplinghook.items.magnetBow;
import com.yyon.grapplinghook.items.multiBow;
import com.yyon.grapplinghook.items.repeller;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class ClientProxyClass extends CommonProxyClass {
	public boolean leftclick = false;
	public boolean prevleftclick = false;
	public HashMap<Integer, Long> enderlaunchtimer = new HashMap<Integer, Long>();
	public final int reusetime = 50;
	
	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
	}
	
	/*
	public ModelResourceLocation grapplinghookloc = new ModelResourceLocation("grapplemod:grapplinghook", "inventory");
	public ModelResourceLocation hookshotloc = new ModelResourceLocation("grapplemod:hookshot", "inventory");
	public ModelResourceLocation enderhookloc = new ModelResourceLocation("grapplemod:enderhook", "inventory");
	public ModelResourceLocation magnetbowloc = new ModelResourceLocation("grapplemod:magnetbow", "inventory");
	public ModelResourceLocation ropeloc = new ModelResourceLocation("grapplemod:rope", "inventory");
	public ModelResourceLocation hookshotropeloc = new ModelResourceLocation("grapplemod:hookshotrope", "inventory");
	public ModelResourceLocation repellerloc = new ModelResourceLocation("grapplemod:repeller", "inventory");
	public ModelResourceLocation repelleronloc = new ModelResourceLocation("grapplemod:repelleron", "inventory");
	public ModelResourceLocation multihookloc = new ModelResourceLocation("grapplemod:multihook", "inventory");
	public ModelResourceLocation multihookropeloc = new ModelResourceLocation("grapplemod:multihookrope", "inventory");
	
	private void registerItemModels() {
		ModelLoader.setCustomMeshDefinition(grapplemod.grapplebowitem, new ItemMeshDefinition() {
		    @Override
		    public ModelResourceLocation getModelLocation(ItemStack stack) {
		    	if (ClientProxyClass.isactive(stack)) {
		    		return ropeloc;
		    	}
		    	return grapplinghookloc;
		    }
		});
		ModelBakery.registerItemVariants(grapplemod.grapplebowitem, grapplinghookloc);
		ModelBakery.registerItemVariants(grapplemod.grapplebowitem, ropeloc);
		ModelLoader.setCustomMeshDefinition(grapplemod.hookshotitem, new ItemMeshDefinition() {
		    @Override
		    public ModelResourceLocation getModelLocation(ItemStack stack) {
		    	if (ClientProxyClass.isactive(stack)) {
		    		return hookshotropeloc;
		    	}
		    	return hookshotloc;
		    }
		});
		ModelBakery.registerItemVariants(grapplemod.hookshotitem, hookshotloc);
		ModelBakery.registerItemVariants(grapplemod.hookshotitem, hookshotropeloc);
		registerItemModel(grapplemod.launcheritem);
		registerItemModel(grapplemod.longfallboots);
		ModelLoader.setCustomMeshDefinition(grapplemod.enderhookitem, new ItemMeshDefinition() {
		    @Override
		    public ModelResourceLocation getModelLocation(ItemStack stack) {
		    	if (ClientProxyClass.isactive(stack)) {
		    		return ropeloc;
		    	}
		    	return enderhookloc;
		    }
		});
		ModelBakery.registerItemVariants(grapplemod.enderhookitem, enderhookloc);
		ModelBakery.registerItemVariants(grapplemod.enderhookitem, ropeloc);
		ModelLoader.setCustomMeshDefinition(grapplemod.magnetbowitem, new ItemMeshDefinition() {
		    @Override
		    public ModelResourceLocation getModelLocation(ItemStack stack) {
		    	if (ClientProxyClass.isactive(stack)) {
		    		return ropeloc;
		    	}
		    	return magnetbowloc;
		    }
		});
		ModelBakery.registerItemVariants(grapplemod.magnetbowitem, magnetbowloc);
		ModelBakery.registerItemVariants(grapplemod.magnetbowitem, ropeloc);
		ModelLoader.setCustomMeshDefinition(grapplemod.repelleritem, new ItemMeshDefinition() {
		    @Override
		    public ModelResourceLocation getModelLocation(ItemStack stack) {
		    	if (ClientProxyClass.isactive(stack)) {
		    		return repelleronloc;
		    	}
		    	return repellerloc;
		    }
		});
		ModelBakery.registerItemVariants(grapplemod.repelleritem, repellerloc);
		ModelBakery.registerItemVariants(grapplemod.repelleritem, repelleronloc);
		ModelLoader.setCustomMeshDefinition(grapplemod.multihookitem, new ItemMeshDefinition() {
		    @Override
		    public ModelResourceLocation getModelLocation(ItemStack stack) {
		    	if (ClientProxyClass.isactive(stack)) {
		    		return multihookropeloc;
		    	}
		    	return multihookloc;
		    }
		});
		ModelBakery.registerItemVariants(grapplemod.multihookitem, multihookloc);
		ModelBakery.registerItemVariants(grapplemod.multihookitem, multihookropeloc);
	}

	private void registerItemModel(Item item) {
		registerItemModel(item, Item.REGISTRY.getNameForObject(item).toString());
	}

	private void registerItemModel(Item item, String modelLocation) {
		final ModelResourceLocation fullModelLocation = new ModelResourceLocation(modelLocation, "inventory");
//		ModelBakery.registerItemVariants(item, fullModelLocation); // Ensure the custom model is loaded and prevent the default model from being loaded
		ModelLoader.setCustomModelResourceLocation(item, 0, fullModelLocation);
	}
	*/
	
	@Override
	public void init(FMLInitializationEvent event, grapplemod grappleModInst) {
		super.init(event, grappleModInst);
		RenderGrappleArrow rga = new RenderGrappleArrow(Items.iron_pickaxe);
		RenderingRegistry.registerEntityRenderingHandler(grappleArrow.class, rga);
//		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(grapplemod.grapplebowitem, 0, new ModelResourceLocation("grapplemod:grapplinghook", "inventory"));
//		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(grapplemod.hookshotitem, 0, new ModelResourceLocation("grapplemod:hookshot", "inventory"));
//		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(grapplemod.launcheritem, 0, new ModelResourceLocation("grapplemod:launcheritem", "inventory"));
//		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(grapplemod.longfallboots, 0, new ModelResourceLocation("grapplemod:longfallboots", "inventory"));
//		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(grapplemod.enderhookitem, 0, new ModelResourceLocation("grapplemod:enderhook", "inventory"));
	}
	
	public crosshairRenderer crosshairrenderer;
	
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
		
		crosshairrenderer = new crosshairRenderer();
	}
	
	@Override
	public void getplayermovement(grappleController control, int playerid) {
		Entity entity = control.entity;
		if (entity instanceof EntityPlayerSP) {
			EntityPlayerSP player = (EntityPlayerSP) entity;
			control.receivePlayerMovementMessage(player.moveStrafing, player.moveForward, player.movementInput.jump);
		}
	}
	
	@SubscribeEvent
	public void onClientTick(TickEvent.ClientTickEvent event) {
		EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		if (player != null) {
			if (!Minecraft.getMinecraft().isGamePaused() || !Minecraft.getMinecraft().isSingleplayer()) {
				try {
					Collection<grappleController> controllers = grapplemod.controllers.values();
					for (grappleController controller : controllers) {
						controller.doClientTick();
					}
				} catch (ConcurrentModificationException e) {
					System.out.println("ConcurrentModificationException caught");
				}
				
				leftclick = (GameSettings.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindAttack) && Minecraft.getMinecraft().currentScreen == null);
				if (prevleftclick != leftclick) {
					if (player != null) {
						ItemStack stack = player.getHeldItem();
						if (stack != null) {
							Item item = stack.getItem();
							if (item instanceof clickitem) {
								if (leftclick) {
									((clickitem)item).onLeftClick(stack, player);
								} else {
									((clickitem)item).onLeftClickRelease(stack, player);
								}
							}
						}
					}
				}
				
				prevleftclick = leftclick;
				
				if (player.onGround) {
					if (enderlaunchtimer.containsKey(player.getEntityId())) {
						long timer = player.worldObj.getTotalWorldTime() - enderlaunchtimer.get(player.getEntityId());
						if (timer > 10) {
							this.resetlaunchertime(player.getEntityId());
						}
					}
				}
			}
		}
	}
	
	@Override
	public void launchplayer(EntityPlayer player) {
		long prevtime;
		if (enderlaunchtimer.containsKey(player.getEntityId())) {
			prevtime = enderlaunchtimer.get(player.getEntityId());
		} else {
			prevtime = 0;
		}
		long timer = player.worldObj.getTotalWorldTime() - prevtime;
		if (timer > reusetime) {
			if (player.getHeldItem().getItem() instanceof enderBow || player.getHeldItem().getItem() instanceof launcherItem) {
				enderlaunchtimer.put(player.getEntityId(), player.worldObj.getTotalWorldTime());
				
	        	vec facing = new vec(player.getLookVec());
//				vec playermotion = vec.motionvec(player);
//				vec newvec = playermotion.add(facing.mult(3));
				
				/*
				if (!grapplemod.controllers.containsKey(player.getEntityId())) {
					player.motionX = newvec.x;
					player.motionY = newvec.y;
					player.motionZ = newvec.z;
					
					if (player instanceof EntityPlayerMP) {
						((EntityPlayerMP) player).connection.sendPacket(new SPacketEntityVelocity(player));
					} else {
						grapplemod.network.sendToServer(new PlayerMovementMessage(player.getEntityId(), player.posX, player.posY, player.posZ, player.motionX, player.motionY, player.motionZ));
					}
				} else {
					facing.mult_ip(3);
					grapplemod.receiveEnderLaunch(player.getEntityId(), facing.x, facing.y, facing.z);
				}
				*/
				if (!grapplemod.controllers.containsKey(player.getEntityId())) {
					player.onGround = false;
					grapplemod.createControl(grapplemod.AIRID, -1, player.getEntityId(), player.worldObj, new vec(0,0,0), 0, null);
				}
				facing.mult_ip(3);
				grapplemod.receiveEnderLaunch(player.getEntityId(), facing.x, facing.y, facing.z);
			}
		}
	}
	
	@Override
	public void resetlaunchertime(int playerid) {
		if (enderlaunchtimer.containsKey(playerid)) {
			enderlaunchtimer.put(playerid, (long) 0);
		}
	}
	
	@Override
	public boolean isSneaking(Entity entity) {
		if (entity == Minecraft.getMinecraft().thePlayer) {
			return (GameSettings.isKeyDown(Minecraft.getMinecraft().gameSettings.keyBindSneak));
		} else {
			return entity.isSneaking();
		}
	}
	
	@Override
    public void blockbreak(BreakEvent event) {
		BlockPos pos = new BlockPos(event.x, event.y, event.z);
		if (pos != null) {
			if (grapplemod.controllerpos.containsKey(pos)) {
				grappleController control = grapplemod.controllerpos.get(pos);

				control.unattach();
				
				grapplemod.controllerpos.remove(pos);
			}
		}
    }
	
	@Override
	public void handleDeath(Entity entity) {
		int id = entity.getEntityId();
		if (grapplemod.controllers.containsKey(id)) {
			grappleController controller = grapplemod.controllers.get(id);
			controller.unattach();
		}
	}
	
	@Override
	public String getkeyname(CommonProxyClass.keys keyenum) {
		KeyBinding binding = null;
		
		GameSettings gs = Minecraft.getMinecraft().gameSettings;
		
		if (keyenum == keys.keyBindAttack) {
			binding = gs.keyBindAttack;
		} else if (keyenum == keys.keyBindBack) {
			binding = gs.keyBindBack;
		} else if (keyenum == keys.keyBindForward) {
			binding = gs.keyBindForward;
		} else if (keyenum == keys.keyBindJump) {
			binding = gs.keyBindJump;
		} else if (keyenum == keys.keyBindLeft) {
			binding = gs.keyBindLeft;
		} else if (keyenum == keys.keyBindRight) {
			binding = gs.keyBindRight;
		} else if (keyenum == keys.keyBindSneak) {
			binding = gs.keyBindSneak;
		} else if (keyenum == keys.keyBindUseItem) {
			binding = gs.keyBindUseItem;
		}
		
		if (binding == null) {
			return "";
		}
		
		
		String displayname = binding.getKeyDescription();
		if (displayname.equals("Button 1")) {
			return "Left Click";
		} else if (displayname.equals("Button 2")) {
			return "Right Click";
		} else {
			return displayname;
		}
	}

	public static boolean isactive(ItemStack stack) {
		EntityPlayer p = Minecraft.getMinecraft().thePlayer;
//		if (p.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND) == stack || p.getItemStackFromSlot(EntityEquipmentSlot.OFFHAND) == stack) {
			int entityid = p.getEntityId();
			if (grapplemod.controllers.containsKey(entityid)) {
				Item item = stack.getItem();
				grappleController controller = grapplemod.controllers.get(entityid);
				if (item.getClass() == grappleBow.class && controller.controllerid == grapplemod.GRAPPLEID) {
					return true;
				} else if (item.getClass() == enderBow.class && controller.controllerid == grapplemod.ENDERID) {
					return true;
				} else if (item.getClass() == hookBow.class && controller.controllerid == grapplemod.HOOKID) {
					return true;
				} else if (item.getClass() == magnetBow.class && controller.controllerid == grapplemod.MAGNETID) {
					return true;
				} else if (item.getClass() == repeller.class && controller.controllerid == grapplemod.REPELID) {
					return true;
				} else if (item.getClass() == multiBow.class && controller.controllerid == grapplemod.MULTIID) {
					return true;
				}
			}
//		}
		return false;
	}
}
