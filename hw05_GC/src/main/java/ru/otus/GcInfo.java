package ru.otus;

import com.sun.management.GarbageCollectionNotificationInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Data
@AllArgsConstructor
public class GcInfo {

    static Logger logger = Logger.getLogger(GcInfo.class.getName());

    private final List<GcStats> youngGenList = new ArrayList<>();
    private final List<GcStats> oldGenList = new ArrayList<>();


    public void addNotification(final GarbageCollectionNotificationInfo info) {
        if (getType(info).equals("Young Generation")) {
            youngGenList.add(GcStats.builder()
                    .name(info.getGcName())
                    .duration(info.getGcInfo().getDuration())
                    .build());
        } else {
            oldGenList.add(GcStats.builder()
                    .name(info.getGcName())
                    .duration(info.getGcInfo().getDuration())
                    .build());
        }
    }

    private String getType(final GarbageCollectionNotificationInfo info) {
        String type = info.getGcAction();
        if (type.equals("end of minor GC")) {
            type = "Young Generation";
        } else if (type.equals("end of major GC")) {
            type = "Old Generation";
        }
        return type;
    }

    public void printData() {
        logger.info("Printing GC stats once a minute:");

        if (youngGenList.isEmpty() && oldGenList.isEmpty()) {
            logger.info("No statistics for the minute");
            return;
        }

        if (!youngGenList.isEmpty()) {
            final String youngGenName = youngGenList.get(0).getName();
            long youngGenDuration = 0;
            for (GcStats stats : youngGenList) {
                youngGenDuration += stats.getDuration();
            }
            long averageYoungGenDuration = youngGenDuration / youngGenList.size();

            logger.info("%s. Name : %s, Count %s, Total duration : %s ms, Avg duration : %s ms%n " +
                    "Young Generation " + youngGenName + " " + youngGenList.size() + " " + youngGenDuration + " " + averageYoungGenDuration);
        }

        if (!oldGenList.isEmpty()) {
            final String olgGenName = oldGenList.get(0).getName();
            long oldGenDuration = 0;
            for (GcStats stats : oldGenList) {
                oldGenDuration += stats.getDuration();
            }
            final long averageOldGenDuration = oldGenDuration / oldGenList.size();
            logger.info("%s. Name : %s, Count %s, Total duration : %s ms, Avg duration : %s ms " +
                    "Old Generation " + " " + olgGenName + " " + oldGenList.size() + " " + oldGenDuration + " " + averageOldGenDuration);

        }
        youngGenList.clear();
        oldGenList.clear();

    }
}
