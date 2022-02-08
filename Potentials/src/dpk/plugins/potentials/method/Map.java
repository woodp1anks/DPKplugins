package dpk.plugins.potentials.method;

import dpk.plugins.potentials.Potentials;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Date;
import java.util.List;

public class Map {
    public static boolean isFirstPlay(String player,String map) {
        return !Potentials.config.contains("data." + player + "-played-" + map);
    }
    public static void setPlayed(String player,String map) {
        Potentials.config.set("data." + player + "-played-" + map,true);
    }
    public static boolean checkRKSReq(String player,String map) {
        int rks = RKS.get(player);
        int rksReq = Potentials.config.getInt("maps." + map + ".rksReq");
        return rks >= rksReq;
    }
    public static boolean checkPTTReq(String player,String map) {
        double ptt = PTT.get(player);
        double pttReq = Potentials.config.getDouble("maps." + map + ".pttReq");
        return ptt >= pttReq;
    }
    public static boolean checkIsPlayingMap(String player) {
        return Potentials.config.contains("data.playing-map-" + player);
    }
    public static String getPlayingMap(String player) {
        return Potentials.config.getString("data.playing-map-" + player);
    }
    public static void endPlaying(String player) {
        Potentials.config.set("data.playing-map-" + player,null);
        Potentials.config.set("data.start-map-time-" + player,null);
    }
    public static void reStart(String player) {
        if (!checkIsPlayingMap(player)) {
            return;
        }
        Player player1 = Bukkit.getPlayer(player);
        long restartTime = new Date().getTime();
        Potentials.config.set("data.start-map-time-" + player,restartTime);
        player1.sendMessage(Config.getMsg("success-restart-timing"));
    }
    public static void begin(String player,String map) {
        // 查看玩家是不是正在玩
        if (checkIsPlayingMap(player)) {
            Player player1 = Bukkit.getPlayer(player);
            player1.performCommand("stopplay");
        }
        // 符不符合要求
        if (!checkRKSReq(player,map) || !checkPTTReq(player, map)) {
            Player player1 = Bukkit.getPlayer(player);
            player1.sendMessage(Config.getMsg("no-enough"));
            return;
        }
        // 获取地图起点
        Player player1 = Bukkit.getPlayer(player);
        World world = Bukkit.getWorld(Potentials.config.getString("maps." + map + ".world"));
        double x = Potentials.config.getDouble("maps." + map + ".pos.x");
        double y = Potentials.config.getDouble("maps." + map + ".pos.y");
        double z = Potentials.config.getDouble("maps." + map + ".pos.z");
        float yaw = Potentials.config.getInt("maps." + map + ".pos.yaw");
        float pitch = Potentials.config.getInt("maps." + map + ".pos.pitch");
        Location mapPos = new Location(world,x,y,z,yaw,pitch);
        // 将玩家传送至起点
        player1.teleport(mapPos);
        player1.sendMessage(Config.getMsg("start-play-map") + map);
        Potentials.config.set("data.playing-map-" + player,map);
        // 开始计时,存储开始时的计时
        long startTime = new Date().getTime();
        Potentials.config.set("data.start-map-time-" + player,startTime);
    }
    public static void done(String player,String map) {
        Player player1 = Bukkit.getPlayer(player);
        // 检查玩家是不是根本就没在玩地图
        if (!checkIsPlayingMap(player)) {
            player1.sendMessage(Config.getMsg("not-playing-map"));
            return;
        }
        // 检查玩家正在游玩的地图是否和终点对应的地图相符
        if (!map.equals(getPlayingMap(player))) {
            player1.sendMessage(Config.getMsg("playing-other-map"));
            return;
        }
        // 停止计时,获取游玩时长
        long endTime = new Date().getTime();
        long startTime = Potentials.config.getLong("data.start-map-time-" + player);
        long playTime = endTime - startTime;
        long playSecond = playTime / 1000;
        long playTicks = playTime / 50;
        Potentials.config.set("data.start-map-time-" + player,null);
        Potentials.config.set("data.play-second-" + player,playSecond);
        // 加入排行榜
        addToBroad(player,Math.round(playTicks),map);
        // 奖励，公告，结束
        RKS.give(player,Potentials.config.getInt("maps." + map + ".finishRKS"));
        if (isFirstPlay(player, map)) {
            PTT.give(player,Potentials.config.getDouble("maps." + map + ".finishRKS") / 100);
            setPlayed(player, map);
        }
        Bukkit.broadcastMessage(Config.getMsg("finish-map-front") + player1.getDisplayName() + Config.getMsg("finish-map-middle") + map + Config.getMsg("finish-map-after") + playSecond + " S " + playTicks + " Ticks");
        endPlaying(player);
    }
    public static void addToBroad(String player,int time,String map) {
        // 首先获取原排行榜
        List<String> playerBroad = Potentials.config.getStringList("maps." + map + ".player-broad");
        List<Integer> timeBroad = Potentials.config.getIntegerList("maps." + map + ".time-broad");
        // 如果没人玩
        if (playerBroad.isEmpty() || timeBroad.isEmpty()) {
            playerBroad.add(0,player);
            timeBroad.add(0,time);
            playerBroad.add(1,"null");
            timeBroad.add(1,99999);
            playerBroad.add(2,"null2");
            timeBroad.add(2,999999);
            playerBroad.add(3,"null3");
            timeBroad.add(3,9999999);
            playerBroad.add(4,"null4");
            timeBroad.add(4,100000000);
            playerBroad.add(5,"null5");
            timeBroad.add(5,1145141911);
            playerBroad.add(6,"null6");
            timeBroad.add(6,2145141911);
            playerBroad.add(7,"null7");
            timeBroad.add(7,944444444);
            playerBroad.add(8,"null8");
            timeBroad.add(8,988888888);
            playerBroad.add(9,"null9");
            timeBroad.add(9,1999199999);
            Potentials.config.set("maps." + map + ".player-broad",playerBroad);
            Potentials.config.set("maps." + map + ".time-broad",timeBroad);
            return;
        }

        // 排行榜上对应的名额
        String dp1 = playerBroad.get(0);
        String dp2 = playerBroad.get(1);
        String dp3 = playerBroad.get(2);
        String dp4 = playerBroad.get(3);
        String dp5 = playerBroad.get(4);
        String dp6 = playerBroad.get(5);
        String dp7 = playerBroad.get(6);
        String dp8 = playerBroad.get(7);
        String dp9 = playerBroad.get(8);
        String dp10 = playerBroad.get(9);
        String p1 = dp1;
        String p2 = dp2;
        String p3 = dp3;
        String p4 = dp4;
        String p5 = dp5;
        String p6 = dp6;
        String p7 = dp7;
        String p8 = dp8;
        String p9 = dp9;
        String p10 = dp10;
        int dt1 = timeBroad.get(0);
        int dt2 = timeBroad.get(1);
        int dt3 = timeBroad.get(2);
        int dt4 = timeBroad.get(3);
        int dt5 = timeBroad.get(4);
        int dt6 = timeBroad.get(5);
        int dt7 = timeBroad.get(6);
        int dt8 = timeBroad.get(7);
        int dt9 = timeBroad.get(8);
        int dt10 = timeBroad.get(9);
        int t1 = dt1;
        int t2 = dt2;
        int t3 = dt3;
        int t4 = dt4;
        int t5 = dt5;
        int t6 = dt6;
        int t7 = dt7;
        int t8 = dt8;
        int t9 = dt9;
        int t10 = dt10;
        // 开始时间检查
        if (time < dt10) {
            // 如果玩家已经在排行榜里了
            if (player.equals(dp1) || player.equals(dp2) || player.equals(dp3) || player.equals(dp4) || player.equals(dp5) || player.equals(dp6) || player.equals(dp7) || player.equals(dp8) || player.equals(dp9) || player.equals(dp10)) {
                if (player.equals(dp1)) {
                    if (time < dt1) {
                        t1 = time;
                    }
                } else if (player.equals(dp2)) {
                    if (time < dt2) {
                        if (time < dt1) {
                            p1 = player;
                            t1 = time;
                            p2 = dp1;
                            t2 = dt1;
                        } else {
                            t2 = time;
                        }
                    }
                } else if (player.equals(dp3)) {
                    if (time < dt3) {
                        if (time < dt2) {
                            if (time < dt1) {
                                p1 = player;
                                t1 = time;
                                p2 = dp1;
                                t2 = dt1;
                                p3 = dp2;
                                t3 = dt2;
                            } else if (time > dt1) {
                                p2 = player;
                                t2 = time;
                                p3 = dp2;
                                t3 = dt2;
                            }
                        } else if (time > dt2 && !(time > dt1)){
                            t3 = time;
                        }
                    }
                } else if (player.equals(dp4)) {
                    if (time < dt4) {
                        if (time < dt3) {
                            if (time < dt2) {
                                if (time < dt1) {
                                    p1 = player;
                                    t1 = time;
                                    p2 = dp1;
                                    t2 = dt1;
                                    p3 = dp2;
                                    t3 = dt2;
                                    p4 = dp3;
                                    t4 = dt3;
                                } else if (time > dt1) {
                                    p2 = player;
                                    t2 = time;
                                    p3 = dp2;
                                    t3 = dt2;
                                    p4 = dp3;
                                    t4 = dt3;
                                }
                            } else if (time > dt2 && !(time > dt1)) {
                                p3 = player;
                                t3 = time;
                                p4 = dp3;
                                t4 = dt3;
                            }
                        } else if (time > dt3 && !(time > dt2) && !(time > dt1)) {
                            t4 = time;
                        }
                    }
                } else if (player.equals(dp5)) {
                    if (time < dt5) {
                        if (time < dt4) {
                            if (time < dt3) {
                                if (time < dt2) {
                                    if (time < dt1) {
                                        p1 = player;
                                        t1 = time;
                                        p2 = dp1;
                                        t2 = dt1;
                                        p3 = dp2;
                                        t3 = dt2;
                                        p4 = dp3;
                                        t4 = dt3;
                                        p5 = dp4;
                                        t5 = dt4;
                                    } else if (time > dt1) {
                                        p2 = player;
                                        t2 = time;
                                        p3 = dp2;
                                        t3 = dt2;
                                        p4 = dp3;
                                        t4 = dt3;
                                        p5 = dp4;
                                        t5 = dt4;
                                    }
                                } else if (time > dt2) {
                                    p3 = player;
                                    t3 = time;
                                    p4 = dp3;
                                    t4 = dt3;
                                    p5 = dp4;
                                    t5 = dt4;
                                }
                            } else if (time > dt3) {
                                p4 = player;
                                t4 = time;
                                p5 = dp4;
                                t5 = dt4;
                            }
                        } else if (time > dt4) {
                            t5 = time;
                        }
                    }
                } else if (player.equals(dp6)) {
                    if (time < dt6) {
                        if (time < dt5) {
                            if (time < dt4) {
                                if (time < dt3) {
                                    if (time < dt2) {
                                        if (time < dt1) {
                                            p1 = player;
                                            t1 = time;
                                            p2 = dp1;
                                            t2 = dt1;
                                            p3 = dp2;
                                            t3 = dt2;
                                            p4 = dp3;
                                            t4 = dt3;
                                            p5 = dp4;
                                            t5 = dt4;
                                            p6 = dp5;
                                            t6 = dt5;
                                        } else if (time > dt1) {
                                            p2 = player;
                                            t2 = time;
                                            p3 = dp2;
                                            t3 = dt2;
                                            p4 = dp3;
                                            t4 = dt3;
                                            p5 = dp4;
                                            t5 = dt4;
                                            p6 = dp5;
                                            t6 = dt5;
                                        }
                                    } else if (time > dt2) {
                                        p3 = player;
                                        t3 = time;
                                        p4 = dp3;
                                        t4 = dt3;
                                        p5 = dp4;
                                        t5 = dt4;
                                        p6 = dp5;
                                        t6 = dt5;
                                    }
                                } else if (time > dt3) {
                                    p4 = player;
                                    t4 = time;
                                    p5 = dp4;
                                    t5 = dt4;
                                    p6 = dp5;
                                    t6 = dt5;
                                }
                            } else if (time > dt4) {
                                p5 = player;
                                t5 = time;
                                p6 = dp5;
                                t6 = dt5;
                            }
                        } else if (time > dt5) {
                            t6 = time;
                        }
                    }
                } else if (player.equals(dp7)) {
                    if (time < dt7) {
                        if (time < dt6) {
                            if (time < dt5) {
                                if (time < dt4) {
                                    if (time < dt3) {
                                        if (time < dt2) {
                                            if (time < dt1) {
                                                p1 = player;
                                                t1 = time;
                                                p2 = dp1;
                                                t2 = dt1;
                                                p3 = dp2;
                                                t3 = dt2;
                                                p4 = dp3;
                                                t4 = dt3;
                                                p5 = dp4;
                                                t5 = dt4;
                                                p6 = dp5;
                                                t6 = dt5;
                                                p7 = dp6;
                                                t7 = dt6;
                                            } else if (time > dt1) {
                                                p2 = player;
                                                t2 = time;
                                                p3 = dp2;
                                                t3 = dt2;
                                                p4 = dp3;
                                                t4 = dt3;
                                                p5 = dp4;
                                                t5 = dt4;
                                                p6 = dp5;
                                                t6 = dt5;
                                                p7 = dp6;
                                                t7 = dt6;
                                            }
                                        } else if (time > dt2) {
                                            p3 = player;
                                            t3 = time;
                                            p4 = dp3;
                                            t4 = dt3;
                                            p5 = dp4;
                                            t5 = dt4;
                                            p6 = dp5;
                                            t6 = dt5;
                                            p7 = dp6;
                                            t7 = dt6;
                                        }
                                    } else if (time > dt3) {
                                        p4 = player;
                                        t4 = time;
                                        p5 = dp4;
                                        t5 = dt4;
                                        p6 = dp5;
                                        t6 = dt5;
                                        p7 = dp6;
                                        t7 = dt6;
                                    }
                                } else if (time > dt4) {
                                    p5 = player;
                                    t5 = time;
                                    p6 = dp5;
                                    t6 = dt5;
                                    p7 = dp6;
                                    t7 = dt6;
                                }
                            } else if (time > dt5) {
                                p6 = player;
                                t6 = time;
                                p7 = dp6;
                                t7 = dt6;
                            }
                        } else if (time > dt6) {
                            t7 = time;
                        }
                    }
                } else if (player.equals(dp8)) {
                    if (time < dt8) {
                        if (time < dt7) {
                            if (time < dt6) {
                                if (time < dt5) {
                                    if (time < dt4) {
                                        if (time < dt3) {
                                            if (time < dt2) {
                                                if (time < dt1) {
                                                    p1 = player;
                                                    t1 = time;
                                                    p2 = dp1;
                                                    p3 = dp2;
                                                    p4 = dp3;
                                                    p5 = dp4;
                                                    p6 = dp5;
                                                    p7 = dp6;
                                                    p8 = dp7;
                                                    t2 = dt1;
                                                    t3 = dt2;
                                                    t4 = dt3;
                                                    t5 = dt4;
                                                    t6 = dt5;
                                                    t7 = dt6;
                                                    t8 = dt7;
                                                } else if (time > dt1) {
                                                    p2 = player;
                                                    p3 = dp2;
                                                    p4 = dp3;
                                                    p5 = dp4;
                                                    p6 = dp5;
                                                    p7 = dp6;
                                                    p8 = dp7;
                                                    t2 = time;
                                                    t3 = dt2;
                                                    t4 = dt3;
                                                    t5 = dt4;
                                                    t6 = dt5;
                                                    t7 = dt6;
                                                    t8 = dt7;
                                                }
                                            } else if (time > dt2) {
                                                p3 = player;
                                                p4 = dp3;
                                                p5 = dp4;
                                                p6 = dp5;
                                                p7 = dp6;
                                                p8 = dp7;
                                                t3 = time;
                                                t4 = dt3;
                                                t5 = dt4;
                                                t6 = dt5;
                                                t7 = dt6;
                                                t8 = dt7;
                                            }
                                        } else if (time > dt3) {
                                            p4 = player;
                                            p5 = dp4;
                                            p6 = dp5;
                                            p7 = dp6;
                                            p8 = dp7;
                                            t4 = time;
                                            t5 = dt4;
                                            t6 = dt5;
                                            t7 = dt6;
                                            t8 = dt7;
                                        }
                                    } else if (time > dt4) {
                                        p5 = player;
                                        p6 = dp5;
                                        p7 = dp6;
                                        p8 = dp7;
                                        t5 = time;
                                        t6 = dt5;
                                        t7 = dt6;
                                        t8 = dt7;
                                    }
                                } else if (time > dt5) {
                                    p6 = player;
                                    p7 = dp6;
                                    p8 = dp7;
                                    t6 = time;
                                    t7 = dt6;
                                    t8 = dt7;
                                }
                            } else if (time > dt6) {
                                p7 = player;
                                p8 = dp7;
                                t7 = time;
                                t8 = time;
                            }
                        } else if (time > dt7) {
                            t8 = time;
                        }
                    }
                } else if (player.equals(dp9)) {
                    if (time < dt9) {
                        if (time < dt8) {
                            if (time < dt7) {
                                if (time < dt6) {
                                    if (time < dt5) {
                                        if (time < dt4) {
                                            if (time < dt3) {
                                                if (time < dt2) {
                                                    if (time < dt1) {
                                                        p2 = dp1;
                                                        p3 = dp2;
                                                        p4 = dp3;
                                                        p5 = dp4;
                                                        p6 = dp5;
                                                        p7 = dp6;
                                                        p8 = dp7;
                                                        p9 = dp8;
                                                        t2 = dt1;
                                                        t3 = dt2;
                                                        t4 = dt3;
                                                        t5 = dt4;
                                                        t6 = dt5;
                                                        t7 = dt6;
                                                        t8 = dt7;
                                                        t9 = dt8;
                                                        p1 = player;
                                                        t1 = time;
                                                    } else if (time > dt1) {
                                                        p2 = player;
                                                        p3 = dp2;
                                                        p4 = dp3;
                                                        p5 = dp4;
                                                        p6 = dp5;
                                                        p7 = dp6;
                                                        p8 = dp7;
                                                        p9 = dp8;
                                                        t2 = time;
                                                        t3 = dt2;
                                                        t4 = dt3;
                                                        t5 = dt4;
                                                        t6 = dt5;
                                                        t7 = dt6;
                                                        t8 = dt7;
                                                        t9 = dt8;
                                                    }
                                                } else if (time > dt2) {
                                                    p3 = player;
                                                    p4 = dp3;
                                                    p5 = dp4;
                                                    p6 = dp5;
                                                    p7 = dp6;
                                                    p8 = dp7;
                                                    p9 = dp8;
                                                    t3 = time;
                                                    t4 = dt3;
                                                    t5 = dt4;
                                                    t6 = dt5;
                                                    t7 = dt6;
                                                    t8 = dt7;
                                                    t9 = dt8;
                                                }
                                            } else if (time > dt3) {
                                                p4 = player;
                                                p5 = dp4;
                                                p6 = dp5;
                                                p7 = dp6;
                                                p8 = dp7;
                                                p9 = dp8;
                                                t4 = time;
                                                t5 = dt4;
                                                t6 = dt5;
                                                t7 = dt6;
                                                t8 = dt7;
                                                t9 = dt8;
                                            }
                                        } else if (time > dt4) {
                                            p5 = player;
                                            p6 = dp5;
                                            p7 = dp6;
                                            p8 = dp7;
                                            p9 = dp8;
                                            t5 = time;
                                            t6 = dt5;
                                            t7 = dt6;
                                            t8 = dt7;
                                            t9 = dt8;
                                        }
                                    } else if (time > dt5) {
                                        p6 = player;
                                        p7 = dp6;
                                        p8 = dp7;
                                        p9 = dp8;
                                        t6 = time;
                                        t7 = dt6;
                                        t8 = dt7;
                                        t9 = dt8;
                                    }
                                } else if (time > dt6) {
                                    p7 = player;
                                    p8 = dp7;
                                    p9 = dp8;
                                    t7 = time;
                                    t8 = dt7;
                                    t9 = dt8;
                                }
                            } else if (time > dt7) {
                                p8 = player;
                                p9 = dp8;
                                t8 = time;
                                t9 = dt8;
                            }
                        } else if (time > dt8) {
                            t9 = time;
                        }
                    }
                } else {
                    if (time < dt9) {
                    if (time < dt8) {
                        if (time < dt7) {
                            if (time < dt6) {
                                if (time < dt5) {
                                    if (time < dt4) {
                                        if (time < dt3) {
                                            if (time < dt2) {
                                                if (time < dt1) {
                                                    p2 = dp1;
                                                    p3 = dp2;
                                                    p4 = dp3;
                                                    p5 = dp4;
                                                    p6 = dp5;
                                                    p7 = dp6;
                                                    p8 = dp7;
                                                    p9 = dp8;
                                                    t2 = dt1;
                                                    t3 = dt2;
                                                    t4 = dt3;
                                                    t5 = dt4;
                                                    t6 = dt5;
                                                    t7 = dt6;
                                                    t8 = dt7;
                                                    t9 = dt8;
                                                    p1 = player;
                                                    t1 = time;
                                                    p10 = dp9;
                                                    t10 = dt9;
                                                } else if (time > dt1) {
                                                    p2 = player;
                                                    p3 = dp2;
                                                    p4 = dp3;
                                                    p5 = dp4;
                                                    p6 = dp5;
                                                    p7 = dp6;
                                                    p8 = dp7;
                                                    p9 = dp8;
                                                    t2 = time;
                                                    t3 = dt2;
                                                    t4 = dt3;
                                                    t5 = dt4;
                                                    t6 = dt5;
                                                    t7 = dt6;
                                                    t8 = dt7;
                                                    t9 = dt8;
                                                    p10 = dp9;
                                                    t10 = dt9;
                                                }
                                            } else if (time > dt2) {
                                                p3 = player;
                                                p4 = dp3;
                                                p5 = dp4;
                                                p6 = dp5;
                                                p7 = dp6;
                                                p8 = dp7;
                                                p9 = dp8;
                                                t3 = time;
                                                t4 = dt3;
                                                t5 = dt4;
                                                t6 = dt5;
                                                t7 = dt6;
                                                t8 = dt7;
                                                t9 = dt8;
                                                p10 = dp9;
                                                t10 = dt9;
                                            }
                                        } else if (time > dt3) {
                                            p4 = player;
                                            p5 = dp4;
                                            p6 = dp5;
                                            p7 = dp6;
                                            p8 = dp7;
                                            p9 = dp8;
                                            t4 = time;
                                            t5 = dt4;
                                            t6 = dt5;
                                            t7 = dt6;
                                            t8 = dt7;
                                            t9 = dt8;
                                            p10 = dp9;
                                            t10 = dt9;
                                        }
                                    } else if (time > dt4) {
                                        p5 = player;
                                        p6 = dp5;
                                        p7 = dp6;
                                        p8 = dp7;
                                        p9 = dp8;
                                        t5 = time;
                                        t6 = dt5;
                                        t7 = dt6;
                                        t8 = dt7;
                                        t9 = dt8;
                                        p10 = dp9;
                                        t10 = dt9;
                                    }
                                } else if (time > dt5) {
                                    p6 = player;
                                    p7 = dp6;
                                    p8 = dp7;
                                    p9 = dp8;
                                    t6 = time;
                                    t7 = dt6;
                                    t8 = dt7;
                                    t9 = dt8;
                                    p10 = dp9;
                                    t10 = dt9;
                                }
                            } else if (time > dt6) {
                                p7 = player;
                                p8 = dp7;
                                p9 = dp8;
                                t7 = time;
                                t8 = dt7;
                                t9 = dt8;
                                p10 = dp9;
                                t10 = dt9;
                            }
                        } else if (time > dt7) {
                            p8 = player;
                            p9 = dp8;
                            t8 = time;
                            t9 = dt8;
                            p10 = dp9;
                            t10 = dt9;
                        }
                    } else if (time > dt8) {
                        p9 = player;
                        t9 = time;
                        p10 = dp9;
                        t10 = dt9;
                    }
                } else if (time > dt9) {
                        t10 = time;
                    }
                }
            } else if (time < dt1) {
                p2 = dp1;
                p3 = dp2;
                p4 = dp3;
                p5 = dp4;
                p6 = dp5;
                p7 = dp6;
                p8 = dp7;
                p9 = dp8;
                p10 = dp9;
                t2 = dt1;
                t3 = dt2;
                t4 = dt3;
                t5 = dt4;
                t6 = dt5;
                t7 = dt6;
                t8 = dt7;
                t9 = dt8;
                t10 = dt9;
                p1 = player;
                t1 = time;
            } else if (time < dt2) {
                p3 = dp2;
                p4 = dp3;
                p5 = dp4;
                p6 = dp5;
                p7 = dp6;
                p8 = dp7;
                p9 = dp8;
                p10 = dp9;
                t3 = dt2;
                t4 = dt3;
                t5 = dt4;
                t6 = dt5;
                t7 = dt6;
                t8 = dt7;
                t9 = dt8;
                t10 = dt9;
                p2 = player;
                t2 = time;
            } else if (time < dt3) {
                p4 = dp3;
                p5 = dp4;
                p6 = dp5;
                p7 = dp6;
                p8 = dp7;
                p9 = dp8;
                p10 = dp9;
                t4 = dt3;
                t5 = dt4;
                t6 = dt5;
                t7 = dt6;
                t8 = dt7;
                t9 = dt8;
                t10 = dt9;
                p3 = player;
                t3 = time;
            } else if (time < dt4) {
                p5 = dp4;
                p6 = dp5;
                p7 = dp6;
                p8 = dp7;
                p9 = dp8;
                p10 = dp9;
                t5 = dt4;
                t6 = dt5;
                t7 = dt6;
                t8 = dt7;
                t9 = dt8;
                t10 = dt9;
                p4 = player;
                t4 = time;
            } else if (time < dt5) {
                p6 = dp5;
                p7 = dp6;
                p8 = dp7;
                p9 = dp8;
                p10 = dp9;
                t6 = dt5;
                t7 = dt6;
                t8 = dt7;
                t9 = dt8;
                t10 = dt9;
                p5 = player;
                t5 = time;
            } else if (time < dt6) {
                p7 = dp6;
                p8 = dp7;
                p9 = dp8;
                p10 = dp9;
                t7 = dt6;
                t8 = dt7;
                t9 = dt8;
                t10 = dt9;
                p6 = player;
                t6 = time;
            } else if (time < dt7) {
                p8 = dp7;
                p9 = dp8;
                p10 = dp9;
                t8 = dt7;
                t9 = dt8;
                t10 = dt9;
                p7 = player;
                t7 = time;
            } else if (time < dt8) {
                p9 = dp8;
                p10 = dp9;
                t9 = dt8;
                t10 = dt9;
                p8 = player;
                t8 = time;
            } else if (time < dt9) {
                p10 = dp9;
                t10 = dt9;
                p9 = player;
                t9 = time;
            } else {
                p10 = player;
                t10 = time;
            }
        }
        playerBroad.set(0,p1);
        playerBroad.set(1,p2);
        playerBroad.set(2,p3);
        playerBroad.set(3,p4);
        playerBroad.set(4,p5);
        playerBroad.set(5,p6);
        playerBroad.set(6,p7);
        playerBroad.set(7,p8);
        playerBroad.set(8,p9);
        playerBroad.set(9,p10);
        timeBroad.set(0,t1);
        timeBroad.set(1,t2);
        timeBroad.set(2,t3);
        timeBroad.set(3,t4);
        timeBroad.set(4,t5);
        timeBroad.set(5,t6);
        timeBroad.set(6,t7);
        timeBroad.set(7,t8);
        timeBroad.set(8,t9);
        timeBroad.set(9,t10);
        Potentials.config.set("maps." + map + ".player-broad",playerBroad);
        Potentials.config.set("maps." + map + ".time-broad",timeBroad);
    }
    public static String getNameOnBroad(String map,int top) {
        top = top - 1;
        List<String> playerBroad = Potentials.config.getStringList("maps." + map + ".player-broad");
        return playerBroad.get(top);
    }
    public static int getTimeOnBroad(String map,int top) {
        top = top - 1;
        List<Integer> timeBroad = Potentials.config.getIntegerList("maps." + map + ".time-broad");
        return timeBroad.get(top);
    }
}
