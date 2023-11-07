ARG TARGET_PLATFORM=
ARG TARGET_PLATFORM_OS=
ARG TARGET_PLATFORM_ARCH=

FROM ${TARGET_PLATFORM_ARCH}/debian:buster-slim

RUN echo ${TARGET_PLATFORM}
RUN echo ${TARGET_PLATFORM_OS}
RUN echo ${TARGET_PLATFORM_ARCH}

RUN apt-get update && \
    apt-get install -y curl && \
    rm -rf /var/lib/apt/lists/*

ENTRYPOINT [ "curl" ]
