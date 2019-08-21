package mcjty.rftools.blocks.teleporter;

import mcjty.lib.tileentity.LogicTileEntity;
import mcjty.lib.varia.GlobalCoordinate;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;

public class SimpleDialerTileEntity extends LogicTileEntity {

    private GlobalCoordinate transmitter;
    private Integer receiver;
    private boolean onceMode = false;

    private boolean prevIn = false;

    public SimpleDialerTileEntity() {
    }

    public void update() {
        if (transmitter == null) {
            return;
        }

        if ((powerLevel > 0) == prevIn) {
            return;
        }

        prevIn = powerLevel > 0;
        markDirty();

        if (powerLevel > 0) {
            TeleportDestinations destinations = TeleportDestinations.getDestinations(getWorld());
            BlockPos coordinate = null;
            int dim = 0;
            if (receiver != null) {
                GlobalCoordinate gc = destinations.getCoordinateForId(receiver);
                if (gc != null) {
                    coordinate = gc.getCoordinate();
                    dim = gc.getDimension();
                }
            }

            int dial = TeleportationTools.dial(getWorld(), null, null, transmitter.getCoordinate(), transmitter.getDimension(), coordinate, dim, onceMode);
            if (dial != DialingDeviceTileEntity.DIAL_OK) {
                // @todo some way to report error
            }
        }
    }

    public boolean isOnceMode() {
        return onceMode;
    }

    public void setOnceMode(boolean onceMode) {
        this.onceMode = onceMode;
        markDirtyClient();
    }

    public GlobalCoordinate getTransmitter() {
        return transmitter;
    }

    public Integer getReceiver() {
        return receiver;
    }

    @Override
    public void readRestorableFromNBT(CompoundNBT tagCompound) {
        super.readRestorableFromNBT(tagCompound);
        if (tagCompound.hasKey("transX")) {
            transmitter = new GlobalCoordinate(new BlockPos(tagCompound.getInt("transX"), tagCompound.getInt("transY"), tagCompound.getInt("transZ")), tagCompound.getInt("transDim"));
        } else {
            transmitter = null;
        }
        if (tagCompound.hasKey("receiver")) {
            receiver = tagCompound.getInt("receiver");
        } else {
            receiver = null;
        }
        onceMode = tagCompound.getBoolean("once");
    }

    @Override
    public void writeRestorableToNBT(CompoundNBT tagCompound) {
        super.writeRestorableToNBT(tagCompound);
        if (transmitter != null) {
            tagCompound.putInt("transX", transmitter.getCoordinate().getX());
            tagCompound.putInt("transY", transmitter.getCoordinate().getY());
            tagCompound.putInt("transZ", transmitter.getCoordinate().getZ());
            tagCompound.putInt("transDim", transmitter.getDimension());
        }
        if (receiver != null) {
            tagCompound.putInt("receiver", receiver);
        }
        tagCompound.putBoolean("once", onceMode);
    }
}
