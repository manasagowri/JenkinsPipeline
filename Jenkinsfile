properties([parameters([string(defaultValue: '', description: 'Enter the rhbuild name. For ex: 4.2-rhel-8', name: 'rhbuild', trim: true), string(defaultValue: '', description: 'Enter the inventory file with path. For ex: conf/inventory/rhel-8.2-server-x86_64.yaml. If left empty, the build will be triggered for latest versions of both RHEL-7 and RHEL-8', name: 'inventory', trim: true), string(defaultValue: '', description: 'Enter the repo details, if the build needs be triggered for any specific RHCS version.', name: 'rhs_ceph_repo', trim: true), string(defaultValue: '', description: 'Enter the container image URL if applicable. ', name: 'container_image', trim: true), string(defaultValue: 'red-hat-storage/cephci', description: 'Enter the github repo link for the build. Default will be red-hat-storage/cephci.', name: 'github_repo_link', trim: true), string(defaultValue: 'master', description: 'Enter the git branch to be used for the build. Default will be master.', name: 'git_branch', trim: true), string(defaultValue: '', description: 'Enter the suite name with path for which build needs to be triggered. For ex: suites/nautilus/ansible/sanity_install_prereq.yaml', name: 'suite_name', trim: true), string(defaultValue: '', description: 'Enter the conf file with path for the build. For ex: conf/nautilus/install_prereq_fullconfig.yaml', name: 'global_conf', trim: true), extendedChoice(defaultValue: '', description: '', descriptionPropertyValue: '', multiSelectDelimiter: ',', name: 'downstream_job', quoteValue: false, saveJSONParameterToFile: false, type: 'PT_CHECKBOX', value: 'FG_Job,FG_Job', visibleItemCount: 3)])])

node{
    stage('Stage 1'){
        echo "Build triggered with parameters:"
        echo "${params.rhbuild}"
        echo "${params.inventory}"
        echo "${params.rhs_ceph_repo}"
        echo "${params.container_image}"
        echo "${params.github_repo_link}"
        echo "${params.git_branch}"
        echo "${params.suite_name}"
        echo "${params.global_conf}"
        echo "${params.downstream_job}"
    }
    stage('Trigger downstream job'){
        echo "${params.downstream_job} triggered for ${params.suite_name} and ${params.global_conf}"
        rhbuild = "${params.rhbuild}"
        inventory = "${params.inventory}"
        rhs_ceph_repo = "${params.rhs_ceph_repo}"
        container_image = "${params.container_image}"
        github_repo_link = "${params.github_repo_link}"
        git_branch = "${params.git_branch}"
        suite_name = "${params.suite_name}"
        global_conf = "${params.global_conf}"
        downstream_job = "${params.downstream_job}"
        String[] job_arr;
        job_arr = downstream_job.split(',');
      
        for( String job : job_arr ){
          echo job
          build job: job, parameters: [[$class: 'StringParameterValue', name: 'rhbuild', value: rhbuild], [$class: 'StringParameterValue', name: 'inventory', value: inventory], [$class: 'StringParameterValue', name: 'rhs_ceph_repo', value: rhs_ceph_repo], [$class: 'StringParameterValue', name: 'container_image', value: container_image], [$class: 'StringParameterValue', name: 'github_repo_link', value: github_repo_link], [$class: 'StringParameterValue', name: 'git_branch', value: git_branch], [$class: 'StringParameterValue', name: 'suite_name', value: suite_name], [$class: 'StringParameterValue', name: 'global_conf', value: global_conf]]
        }
    }
}
