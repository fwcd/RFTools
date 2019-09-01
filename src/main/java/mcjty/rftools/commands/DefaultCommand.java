package mcjty.rftools.commands;

public abstract class DefaultCommand {}/* @todo 1.14 implements ICommand {
    protected final Map<String,RfToolsCommand> commands = new HashMap<>();

    public DefaultCommand() {
        registerCommand(new CmdHelp());
    }

    protected void registerCommand(RfToolsCommand command) {
        commands.put(command.getCommand(), command);
    }

    public void showHelp(ICommandSender sender) {
        ITextComponent component1 = new StringTextComponent(TextFormatting.BLUE + getName() + " <subcommand> <args>");
        if (sender instanceof PlayerEntity) {
            ((PlayerEntity) sender).sendStatusMessage(component1, false);
        } else {
            sender.sendMessage(component1);
        }
        for (Map.Entry<String,RfToolsCommand> me : commands.entrySet()) {
            ITextComponent component = new StringTextComponent("    " + me.getKey() + " " + me.getValue().getHelp());
            if (sender instanceof PlayerEntity) {
                ((PlayerEntity) sender).sendStatusMessage(component, false);
            } else {
                sender.sendMessage(component);
            }
        }
    }

    class CmdHelp implements RfToolsCommand {
        @Override
        public String getHelp() {
            return "";
        }

        @Override
        public int getPermissionLevel() {
            return 0;
        }

        @Override
        public boolean isClientSide() {
            return false;
        }

        @Override
        public String getCommand() {
            return "help";
        }

        @Override
        public void execute(ICommandSender sender, String[] args) {
            showHelp(sender);
        }
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return getName() + " <subcommand> <args> (try '" + getName() + " help' for more info)";
    }


    @Override
    public List<String> getAliases() {
        return Collections.emptyList();
    }


    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) {
        World world = sender.getEntityWorld();
        if (args.length <= 0) {
            if (!world.isRemote) {
                showHelp(sender);
            }
        } else {
            RfToolsCommand command = commands.get(args[0]);
            if (command == null) {
                if (!world.isRemote) {
                    ITextComponent component = new StringTextComponent(TextFormatting.RED + "Unknown RfTools command: " + args[0]);
                    if (sender instanceof PlayerEntity) {
                        ((PlayerEntity) sender).sendStatusMessage(component, false);
                    } else {
                        sender.sendMessage(component);
                    }
                }
            } else {
                if (world.isRemote) {
                    // We are client-side. Only do client-side commands.
                    if (command.isClientSide()) {
                        command.execute(sender, args);
                    }
                } else {
                    // Server-side.
                    if (!sender.canUseCommand(command.getPermissionLevel(), getName())) {
                        ITextComponent component = new StringTextComponent(TextFormatting.RED + "Command is not allowed!");
                        if (sender instanceof PlayerEntity) {
                            ((PlayerEntity) sender).sendStatusMessage(component, false);
                        } else {
                            sender.sendMessage(component);
                        }
                    } else {
                        command.execute(sender, args);
                    }
//                    if (!sender.canCommandSenderUseCommand(command.getPermissionLevel(), getCommandName())) {
//                        sender.addChatMessage(new StringTextComponent(TextFormatting.RED + "Command is not allowed!"));
//                    } else {
//                        command.execute(sender, args);
//                    }
                }
            }
        }
    }

    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos pos) {
        return new ArrayList<>();
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public boolean isUsernameIndex(String[] sender, int p_82358_2_) {
        return false;
    }

    @Override
    public int compareTo(ICommand o) {
        return getName().compareTo(o.getName());
    }
}
*/