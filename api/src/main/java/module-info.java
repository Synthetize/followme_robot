module api {
    requires utilities;
    requires java.logging;
    exports it.unicam.cs.followme.list.model;
    exports it.unicam.cs.followme.list.model.robots;
    exports it.unicam.cs.followme.list;
    exports it.unicam.cs.followme.list.model.shapes;
    exports it.unicam.cs.followme.list.utils;
    exports it.unicam.cs.followme.list.model.commands;
    exports it.unicam.cs.followme.list.utils.cloneFactory;
}