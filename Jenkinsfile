properties([
    parameters([
        string(defaultValue: '', description: 'Enter the rhbuild name. For ex: 4.2-rhel-8', name: 'rhbuild', trim: true),
        string(defaultValue: '', description: 'Enter the inventory file name. For ex: rhel-8.2-server-x86_64. If left empty, the build will be triggered for latest versions of both RHEL-7 and RHEL-8', name: 'inventory', trim: true),
        string(defaultValue: '', description: 'Enter the repo details, if the build needs be triggered for any specific RHCS version.', name: 'rhs_ceph_repo', trim: true),
        string(defaultValue: '', description: 'Enter the container image URL if applicable. ', name: 'container_image', trim: true),
        string(defaultValue: 'red-hat-storage/cephci', description: 'Enter the github repo link for the build. Default will be red-hat-storage/cephci.', name: 'github_repo_link', trim: true),
        string(defaultValue: 'master', description: 'Enter the git branch to be used for the build. Default will be master.', name: 'git_branch', trim: true),
        extendedChoice(defaultValue: '', description: '', descriptionPropertyValue: '', multiSelectDelimiter: ',', name: 'downstream_job', quoteValue: false,
            saveJSONParameterToFile: false, type: 'PT_CHECKBOX', value: 'FG_Job,FG_Job', visibleItemCount: 3)
    ])
])

node{
  stages{
    stage('Trigger downstream job'){
      steps{
        echo "inside downstream job"
        rhbuild = "${params.rhbuild}"
        inventory = "${params.inventory}"
        rhs_ceph_repo = "${params.rhs_ceph_repo}"
        container_image = "${params.container_image}"
        github_repo_link = "${params.github_repo_link}"
        git_branch = "${params.git_branch}"
        suite_name = "sanity_ceph_ansible"
        global_conf = "sanity-ceph-ansible"
        downstream_job = "${params.downstream_job}"
        echo downstream_job
        echo "params fetched"
        String[] job_arr;
        job_arr = downstream_job.split(',')[0];
        parallel{
          for( String job : job_arr ){
              job: {
               stage('trigger'){
                   echo job_arr
                   steps{
                      build job: job_arr, parameters: [ [$class: 'StringParameterValue', name: 'rhbuild', value: rhbuild],
                                                 [$class: 'StringParameterValue', name: 'inventory', value: inventory],
                                                 [$class: 'StringParameterValue', name: 'rhs_ceph_repo', value: rhs_ceph_repo],
                                                 [$class: 'StringParameterValue', name: 'container_image', value: container_image],
                                                 [$class: 'StringParameterValue', name: 'github_repo_link', value: github_repo_link],
                                                 [$class: 'StringParameterValue', name: 'git_branch', value: git_branch],
                                                 [$class: 'StringParameterValue', name: 'suite_name', value: suite_name],
                                                 [$class: 'StringParameterValue', name: 'global_conf', value: global_conf] ]
                   }
               }
           }
         }
        }
      }
    }
  }
}
