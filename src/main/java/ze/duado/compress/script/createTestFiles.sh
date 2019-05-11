#!/bin/bash
# remove old data
rm -rf /var/tmp/zero*
rm -rf /var/tmp/random*
rm -rf /var/tmp/semiRandom*

# null file
dd if=/dev/zero of=/var/tmp/zero count=1024 bs=3072
# random file
dd if=/dev/urandom of=/var/tmp/random bs=1024 count=3072
# random file with zero in middle
dd if=/dev/urandom of=/var/tmp/semiRandom bs=1024 count=1024
dd if=/dev/zero of=/var/tmp/nullrandom2 count=1024 bs=1024
dd if=/dev/urandom of=/var/tmp/nullrandom3 bs=1024 count=1024
cat /var/tmp/nullrandom2 >> /var/tmp/semiRandom
cat /var/tmp/nullrandom3 >> /var/tmp/semiRandom
# remove temp data
rm -rf /var/tmp/nullrandom2 /var/tmp/nullrandom3
