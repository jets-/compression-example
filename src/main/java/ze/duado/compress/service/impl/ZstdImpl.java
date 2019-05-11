package ze.duado.compress.service.impl;

import com.github.luben.zstd.Zstd;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ze.duado.compress.service.Compress;

@Service
@Qualifier("zstd")
public class ZstdImpl implements Compress {

    @Override
    public String getName() {
        return "zstd";
    }

    @Override
    public byte[] compress(byte[] data) {
        return Zstd.compress(data);
    }

    @Override
    public byte[] decompress(byte[] data) {
        byte[] restored = new byte[1];
        Zstd.decompress(restored, data);
        return restored;
    }

    @Override
    public byte[] decompress(byte[] data, Integer value) {
        return Zstd.decompress(data, value);
    }
}
