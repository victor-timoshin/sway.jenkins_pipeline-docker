ARG TARGET_PLATFORM_ARCH=

FROM ${TARGET_PLATFORM_ARCH}/debian:buster-slim AS module_x-release

ARG TARGET_PLATFORM=

RUN echo "${TARGET_PLATFORM}"

RUN apt-get update && \
    apt-get install -y curl && \
    rm -rf /var/lib/apt/lists/*

ENTRYPOINT [ "curl" ]
