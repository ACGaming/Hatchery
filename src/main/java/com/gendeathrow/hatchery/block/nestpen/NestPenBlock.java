package com.gendeathrow.hatchery.block.nestpen;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.gendeathrow.hatchery.Hatchery;

public class NestPenBlock extends Block implements ITileEntityProvider
{
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
    protected static final AxisAlignedBB AABB = new AxisAlignedBB(0.00D, 0.0D, 0.00, 1.0D, 1.0D, 1.0D);
    
    
    protected static final AxisAlignedBB NORTH_STAIRS_AABB = new AxisAlignedBB(0.25D, 0.0D, 0.0D, 0.6875D,0.125D, 0.1875D);
    protected static final AxisAlignedBB NORTH_BASE_AABB = new AxisAlignedBB(0.125D, 0.0D, 0.25D, 0.9375D,0.125D, 1.0D);
    protected static final AxisAlignedBB NORTH_SOUTH_WALL_AABB = new AxisAlignedBB(0.125D, 0.1875D, 0.9375D, 0.9375D, 1.6D, 1.0D);
    protected static final AxisAlignedBB NORTH_WALL_AABB = new AxisAlignedBB(0.0625D, 0.1875D, 0.25D, 0.125D, 1.6D, 1.0D);
    
    protected static final AxisAlignedBB NORTH_WEST_WALL_AABB = new AxisAlignedBB(0.0625D, 0.1875D, 0.25D, 0.125D, 1.6D, 1.0D);
    protected static final AxisAlignedBB NORTH_EAST_WALL_AABB = new AxisAlignedBB(0.81250D, 0.1875D, 0.25D, 0.9375D, 1.6D, 1.0D);
    protected static final AxisAlignedBB[] NORTH_FACING_AABB = new AxisAlignedBB[] {NORTH_STAIRS_AABB, NORTH_BASE_AABB, NORTH_SOUTH_WALL_AABB, NORTH_WEST_WALL_AABB, NORTH_EAST_WALL_AABB};
    

    
//    protected static final AxisAlignedBB SOUTH_STAIRS_AABB = new AxisAlignedBB(0.6875D,0.0D, 0.1875D, 0.25D, 0.125D, 0.0D);
//    protected static final AxisAlignedBB SOUTH_BASE_AABB = new AxisAlignedBB(0.9375D, 0.0D, 1.0D, 0.125D,0.125D, 0.25D);
//    protected static final AxisAlignedBB SOUTH_SOUTH_WALL_AABB = new AxisAlignedBB(0.9375D, 0.1875D, 1.0D, 0.125D, 1.6D, 0.9375D);
//    protected static final AxisAlignedBB SOUTH_WEST_WALL_AABB = new AxisAlignedBB(0.125D, 0.1875D, 1.0D, 0.0625D, 1.6D, 0.25D);
//    protected static final AxisAlignedBB SOUTH_EAST_WALL_AABB = new AxisAlignedBB(0.9375D, 0.1875D, 1.0D, 0.81250D, 1.6D, 0.25D); 
//    protected static final AxisAlignedBB[] SOUTH_FACING_AABB = new AxisAlignedBB[] {SOUTH_STAIRS_AABB, SOUTH_BASE_AABB, SOUTH_SOUTH_WALL_AABB, SOUTH_WEST_WALL_AABB, SOUTH_EAST_WALL_AABB};
//   
    //protected static final AxisAlignedBB NORTH_WEST_WALL_AABB = new AxisAlignedBB(0.0625D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D);
    //protected static final AxisAlignedBB NORTH_EAST_WALL_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.875D, 1.0D, 1.0D, 1.0D);
    
    
    protected static final AxisAlignedBB[] BOUNDING_BOXES = new AxisAlignedBB[] {AABB};    

	protected String name;
	
	public NestPenBlock() 
	{
		super(Material.WOOD);
		this.name = "pen";
		this.setUnlocalizedName("pen");
		this.setRegistryName(Hatchery.MODID,"pen");
		this.setCreativeTab(Hatchery.hatcheryTabs);	
		
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
		this.setHardness(5);
	}

	
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer)
    {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    /**
     * Called by ItemBlocks after a block is set in the world, to allow post-place logic
     */
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
    {
        worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);

//        if (stack.hasDisplayName())
//        {
//            TileEntity tileentity = worldIn.getTileEntity(pos);
//
//            if (tileentity instanceof TileEntityDispenser)
//            {
//                ((TileEntityDispenser)tileentity).setCustomName(stack.getDisplayName());
//            }
//        }
    }
    
    private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state)
    {
        if (!worldIn.isRemote)
        {
            IBlockState iblockstate = worldIn.getBlockState(pos.north());
            IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
            IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
            IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);

            if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock())
            {
                enumfacing = EnumFacing.SOUTH;
            }
            else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock())
            {
                enumfacing = EnumFacing.NORTH;
            }
            else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock())
            {
                enumfacing = EnumFacing.EAST;
            }
            else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock())
            {
                enumfacing = EnumFacing.WEST;
            }

            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }
	 
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	  
	@Override
	public boolean isFullBlock(IBlockState state)
	{
		return false;
	}
	
    public boolean isFullCube(IBlockState state)
    {
        return false;
    }
	
    public boolean isFullyOpaque(IBlockState state)
    {
    	return false;
    }
	
    @Override
    public BlockRenderLayer getBlockLayer()
    {
		return BlockRenderLayer.TRANSLUCENT;
    	
    }
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) 
	{
		return new NestPenTileEntity();
	}
	
	public static EnumFacing getFacing(IBlockState blockStateContainer)
	{
		return (EnumFacing)blockStateContainer.getValue(FACING);
		
	}
	
	@Override
	public void addCollisionBoxToList(IBlockState state, World worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Entity entityIn)
	{
//		EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
//		
//		System.out.println(enumfacing.getName());
//		//state = state.getActualState(worldIn, pos);
//		if(enumfacing == EnumFacing.NORTH)
//		{
//			for(AxisAlignedBB aabb: NORTH_FACING_AABB)
//			{
//				addCollisionBoxToList(pos, entityBox, collidingBoxes, aabb);
//			}
//		}
//		
//		else if(enumfacing == EnumFacing.SOUTH)
//		{
//			for(AxisAlignedBB aabb: SOUTH_FACING_AABB)
//			{
//				addCollisionBoxToList(pos, entityBox, collidingBoxes, aabb);
//			}
//		}
//		
//		else
//		{
//			for(AxisAlignedBB aabb: NORTH_FACING_AABB)
//			{
//				addCollisionBoxToList(pos, entityBox, collidingBoxes, aabb);
//			}
//		}
		
//		addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_STAIRS_AABB);
//		addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_BASE_AABB);
//		addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_SOUTH_WALL_AABB);
//		addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_WEST_WALL_AABB);
//		addCollisionBoxToList(pos, entityBox, collidingBoxes, NORTH_EAST_WALL_AABB);
		
//
//		if (((Boolean)state.getValue(hasEgg)).booleanValue())
//		{
//			addCollisionBoxToList(pos, entityBox, collidingBoxes, withEgg_AABB);
//		}
		addCollisionBoxToList(pos, entityBox, collidingBoxes, AABB);
		
		
	}
	
	
	@Override
    @Nullable
    public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, World worldIn, BlockPos pos)
    {
        return NULL_AABB;
    }
    
	@Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
    {
        return BOUNDING_BOXES[0];
    }
    
	@Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, @Nullable ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		return false;
    }
	
    /**
     * Convert the given metadata into a BlockState for this Block
     */
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing enumfacing = EnumFacing.getFront(meta);

        if (enumfacing.getAxis() == EnumFacing.Axis.Y)
        {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }


    /**
     * Returns the blockstate with the given rotation from the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withRotation(IBlockState state, Rotation rot)
    {
        return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
    }

    /**
     * Returns the blockstate with the given mirror of the passed blockstate. If inapplicable, returns the passed
     * blockstate.
     */
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
    }

    protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {FACING});
    }
	

}
