#!/usr/bin/env groovy

def call(body) {
    echo "Call image"

    def img = new sway.jenkins_pipeline.docker.Image("./", "default", "latest")
    script:this.echo(img.name())

    return this
}
