package ze.duado.compress.service.impl;

import net.jpountz.lz4.LZ4Compressor;
import net.jpountz.lz4.LZ4Factory;
import net.jpountz.lz4.LZ4FastDecompressor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ze.duado.compress.service.Compress;

@Service
@Qualifier("lz4")
public class LZ4Impl implements Compress {

    private static LZ4Factory factory = LZ4Factory.fastestInstance();

    @Override
    public String getName() {
        return "lz4";
    }

    @Override
    public byte[] compress(byte[] data) {
        final int decompressedLength = data.length;
        LZ4Compressor compressor = factory.fastCompressor();

        int maxCompressedLength = compressor.maxCompressedLength(decompressedLength);
        byte[] compressed = new byte[maxCompressedLength];
        int compressedLength = compressor.compress(data, 0, decompressedLength, compressed, 0, maxCompressedLength);

        return compressed;
    }

    @Override
    public byte[] decompress(byte[] data) {
        LZ4FastDecompressor decompressor = factory.fastDecompressor();
        byte[] restored = new byte[1000000000];
        int compressedLength = decompressor.decompress(data, restored);

        return restored;
    }

    @Override
    public byte[] decompress(byte[] compressed, Integer decompressedLen) {
        LZ4FastDecompressor decompressor = factory.fastDecompressor();
        byte[] restored = new byte[decompressedLen];
        int compressedLength = decompressor.decompress(compressed, 0, restored, 0, decompressedLen);

        return restored;
    }


}
