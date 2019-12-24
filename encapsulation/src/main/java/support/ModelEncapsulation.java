//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package support;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.core.util.ZipUtil;
import support.data.DataItem;
import support.model.Event;
import support.model.IOFlagEnum;
import support.model.ModelItem;
import support.model.State;
import support.utils.DataUtils;
import support.utils.PathUtils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class ModelEncapsulation implements Model {
    private ModelItem modelItem;

    public ModelEncapsulation(ModelItem modelItem) {
        this.modelItem = modelItem;
    }

    public void run(String instanceId) {
        Path workPath = PathUtils.getInstance(instanceId);
        Path inputGridsPath = workPath.resolve("inputGirds");
        Path outputPath = workPath.resolve("output");
        Path sagaExePath = PathUtils.getPublicExe("saga");
        File inputGridsDir = inputGridsPath.toFile();
        String formula = null;
        String useNodata = null;
        String resampling = null;
        Iterator var10 = this.modelItem.getStateList().iterator();

        Iterator var12;
        String name;
        File zipResult;
        while(var10.hasNext()) {
            State state = (State)var10.next();
            var12 = state.getEventList().iterator();

            while(var12.hasNext()) {
                Event event = (Event)var12.next();
                name = event.getName();
                String value = event.getDataTemplate().getValue();
                if (event.getIoFlagEnum().equals(IOFlagEnum.INPUT)) {
                    if ("INPUT".equals(name)) {
                        zipResult = workPath.resolve(Paths.get(event.getName())).toFile();
                        DataUtils.downloadDataItemStore(value, zipResult);
                        ZipUtil.unzip(zipResult, inputGridsDir);
                    } else if ("RESAMPLING".equals(name)) {
                        resampling = value;
                    } else if ("FORMULA".equals(name)) {
                        formula = value;
                    } else if ("USE_NODATA".equals(name)) {
                        ;
                    }
                }
            }
        }

        List<File> files = FileUtil.loopFiles(inputGridsDir, (pathname) -> {
            return pathname.getName().endsWith(".sgrd");
        });
        StringBuilder stringBuilder = new StringBuilder();
        var12 = files.iterator();

        File result;
        while(var12.hasNext()) {
            result = (File)var12.next();
            stringBuilder.append(result.getAbsoluteFile());
            stringBuilder.append(";");
        }

        File outputDir = outputPath.toFile();
        if (!outputDir.exists()) {
            outputDir.mkdir();
        }

        result = outputPath.resolve("result.sgrd").toFile();
        name = sagaExePath.toString() + " grid_calculus 1 -GRIDS " + stringBuilder.toString() + " -RESULT " + result.getAbsolutePath() + " -FORMULA " + formula + " -RESAMPLING " + resampling;
        Process process = RuntimeUtil.exec((String[])null, sagaExePath.getParent().toFile(), new String[]{name});
        RuntimeUtil.getResult(process);
        zipResult = ZipUtil.zip(outputDir.toString());
        Iterator var17 = this.modelItem.getStateList().iterator();

        while(var17.hasNext()) {
            State state = (State)var17.next();
            Iterator var19 = state.getEventList().iterator();

            while(var19.hasNext()) {
                Event event = (Event)var19.next();
                if ("RESULT".equals(event.getName())) {
                    DataItem dataItem = DataUtils.addDataItem(zipResult);
                    event.getDataTemplate().setValue(dataItem.getId());
                    return;
                }
            }
        }

    }
}
